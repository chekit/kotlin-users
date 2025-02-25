package nl.rabobank.org_users_rest.service

import nl.rabobank.org_users_rest.model.User
import nl.rabobank.org_users_rest.repository.UsersRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UsersService(private val repo: UsersRepository) {
    fun getUsers(): MutableList<User.Entity> {
        return repo.findAll()
    }

    fun getUser(id: Int): User {
        return repo.findById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID $id not found");
    }

    fun addUser(data: User.Dto): MutableList<User.Entity> {
        val users: MutableList<User.Entity> = repo.findAll();
        val lastId: Int = users.maxByOrNull { it.id }?.let { it.id + 1 } ?: 0;
        val newUser = User.Entity(lastId, data.firstName, data.lastName, data.role);

        return repo.addOne(newUser);
    }

    fun updateUserById(id: Int, data: User.UpdateDto): User {
        val user =
            repo.findById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID $id not found");

        val updatedUser = user.copy(
            firstName = data.firstName ?: user.firstName,
            lastName = data.lastName ?: user.lastName,
            role = data.role ?: user.role
        );

        return repo.updateOne(updatedUser);
    }

    fun deleteUserById(id: Int): String {
        return repo.deleteOne(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID $id not found")
    }
}