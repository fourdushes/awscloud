public interface UserRepository extends JpaRepository<User, Long> {

    String findIdByNameAndEmail(String name, String email);

}
