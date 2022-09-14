package dev.guillem.githubbrowserlab.data

import dev.guillem.githubbrowserlab.data.mapper.UserMapper
import dev.guillem.githubbrowserlab.factory.UserFactory
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class UsersDataRepositoryTest {
    private lateinit var usersDataRepository: UsersDataRepository
    private val usersDataSourceMock: UsersDataSource = mock()
    private val userMapperMock: UserMapper = mock()

    @Before
    fun setUp() {
        usersDataRepository = UsersDataRepository(usersDataSourceMock, userMapperMock)
    }

    @Test
    fun `Should get users from csv from users data source`() {
        val userEntities = UserFactory.makeUserEntityList(2)
        whenever(usersDataSourceMock.getUsersFromCSV()).thenReturn(userEntities)

        val testObserver = usersDataRepository.getUsersFromCSV().test()

        verify(usersDataSourceMock).getUsersFromCSV()
        testObserver.assertComplete()
    }

    @Test
    fun `Should get users from csv from users data source and return data`() {
        val users = UserFactory.makeUserList(2)
        val userEntities = UserFactory.makeUserEntityList(2)
        users.forEachIndexed { index, user ->
            whenever(userMapperMock.mapFromEntity(userEntities[index]))
                .thenReturn(user)
        }
        whenever(usersDataSourceMock.getUsersFromCSV()).thenReturn(userEntities)

        val testObserver = usersDataRepository.getUsersFromCSV().test()

        verify(usersDataSourceMock).getUsersFromCSV()
        testObserver.assertValue(users)
    }
}