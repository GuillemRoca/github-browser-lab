package dev.guillem.githubbrowserlab.data;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads TAB delimited files.
 * Supports quoted cells and the main platforms' line endings: Unix {@code \n}, Mac {@code \r}, Windows {@code \r\n}.
 */
public class TSVReader implements Closeable {

    private final List<String> columns = new ArrayList<>();
    private final TSVParser parser;

    /**
     * Creates a TSV reader for the specified input.
     * Any reader will do, but {@link BufferedReader} may give a better performance.
     *
     * @param reader to read the characters from
     */
    public TSVReader(@NonNull Reader reader) {
        this.parser = new TSVParser(reader);
    }

    /**
     * Parses and returns the cells from the next row in the input.
     *
     * @return cells as an array for the current row, quotes are removed around quoted cells and content unescaped.
     * {@code null} is returned when there's no more rows.
     * @throws IOException    if read fails
     * @throws ParseException if the read contents are invalid (e.g. unfinished quoted cell)
     */
    public @Nullable
    String[] readRow() throws IOException {
        if (parser.isEndOfFile()) {
            return null;
        }
        if (parser.isEndOfLine()) {
            parser.consumeEndOfLine();
            return new String[]{""};
        }
        columns.clear();
        while (!parser.isEndOfLine()) {
            columns.add(parser.readCell());
        }
        parser.consumeEndOfLine();
        return columns.toArray(new String[columns.size()]);
    }

    @Override
    public void close() throws IOException {
        parser.close();
    }

    /**
     * Custom exception thrown for TSV format problems.
     */
    public static class ParseException extends IOException {
        ParseException(@NonNull String message) {
            super(message);
        }
    }

    private static class TSVParser implements Closeable {

        private static final char DELIMITER = '\t';
        private static final char QUOTE = '\"';

        private final Reader reader;

        TSVParser(@NonNull Reader reader) {
            if (reader == null) throw new NullPointerException("reader");
            this.reader = buffer(reader);
        }

        /**
         * Wraps the given {@link Reader} to support peeking.
         */
        private @NonNull
        Reader buffer(@NonNull Reader reader) {
            if (!reader.markSupported()) {
                reader = new BufferedReader(reader);
            }
            return reader;
        }

        @CheckResult
        private int consumeSingleCharacter() throws IOException {
            return reader.read();
        }

        private void skipNextCharacter() throws IOException {
            int consumed = consumeSingleCharacter();
            assert -1 != consumed;
        }

        @CheckResult
        private int peekNextCharacter() throws IOException {
            reader.mark(1);
            int value = consumeSingleCharacter();
            reader.reset();
            return value;
        }

        @Override
        public void close() throws IOException {
            reader.close();
        }

        private boolean isEOF(int chr) {
            return chr == -1;
        }

        private boolean isEOL(int chr) {
            return chr == '\n' || chr == '\r' || isEOF(chr);
        }

        boolean isEndOfFile() throws IOException {
            return isEOF(peekNextCharacter());
        }

        boolean isEndOfLine() throws IOException {
            return isEOL(peekNextCharacter());
        }

        /**
         * Reads and hence consumes a platform independent end of line. Idempotent behavior at the end of file.
         *
         * @throws IOException    if reading fails
         * @throws ParseException if there's no end of line at this point
         */
        void consumeEndOfLine() throws IOException {
            int chr = consumeSingleCharacter();
            if (!isEOL(chr)) {
                throw new ParseException("Parser is not at the end of a line.");
            }
            if (chr == '\r' && peekNextCharacter() == '\n') {
                skipNextCharacter(); // \n of Windows \r\n
            }
            // Unix \n and Mac \r already read into chr
        }

        /**
         * Reads a cell that from the reader.
         * After this, the reader will be positioned at the beginning of the next cell or on the line terminator.
         *
         * @return cell contents, unescaped
         */
        @NonNull
        String readCell() throws IOException {
            boolean quoted = peekNextCharacter() == QUOTE;
            if (quoted) {
                return readQuotedCell();
            } else {
                return readRawCell();
            }
        }

        /**
         * Reads a cell that has quotes around it from the reader.
         * After this, the reader will be positioned at the beginning of the next cell or on the line terminator.
         *
         * @return cell contents, with quotes around removed, and quotes inside unescaped
         * @throws IOException    if reading fails
         * @throws ParseException if there's a syntax error: cell must start and end with a quote
         */
        private @NonNull
        String readQuotedCell() throws IOException {
            StringBuilder cell = new StringBuilder();
            if (consumeSingleCharacter() != QUOTE) {
                throw new ParseException("Quoted cell must start with a quote.");
            }
            boolean terminated = false;
            while (!isEndOfFile()) {
                int chr = consumeSingleCharacter();
                if (chr == QUOTE) {
                    chr = peekNextCharacter();
                    if (chr == DELIMITER) {
                        skipNextCharacter(); // consume delimiter
                        terminated = true;
                        break; // end of cell
                    } else if (chr == QUOTE) {
                        skipNextCharacter(); // consume quote
                        // found an escaped quote that is part of the cell
                        cell.append((char) chr);
                        continue; // continue parsing with next
                    } else if (isEOL(chr)) {
                        // don't consume end of line
                        terminated = true;
                        break; // end of cell
                    }
                    throw new ParseException(
                            "Invalid input: quote must be escaped, terminating a cell or terminating the line.");
                } else { // normal character
                    cell.append((char) chr);
                }
            }
            if (!terminated) {
                throw new ParseException("Invalid input: cell \"" + cell + "\" has no terminating quote.");
            }
            return cell.toString();
        }

        /**
         * Reads a cell that has unescaped content.
         * After this, the reader will be positioned at the beginning of the next cell or on the line terminator.
         *
         * @return cell contents, unmodified
         * @throws IOException if reading fails
         */
        private @NonNull
        String readRawCell() throws IOException {
            StringBuilder cell = new StringBuilder();
            while (!isEndOfLine()) {
                int chr = consumeSingleCharacter();
                if (chr == DELIMITER) {
                    break;
                }
                cell.append((char) chr);
            }
            return cell.toString();
        }
    }
}
