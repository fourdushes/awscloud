@RestController("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final GuardUserService guardUserService;
    private final InstitutionsUserService institutionsUserService;

    @PostMapping("/join")
    public Result join(@RequestBody JoinUserRequest request) {
        
        if (request.getUserType() == UserType.WARD()) {
            User user = new User(request.getId(), request.getName(), request.getEmail(), request.getPassword(), request.getUserType());
        } else if (request.getUserType() == UserType.GUARDIAN()) {
            GuardUser guardUser = new GuardUser(request.getId(), request.getName(), request.getEmail(), request.getPassword(), request.getUserType());
        } else if (request.getUserType() == UserType.INSTITUTIONS()) {
            InstitutionsUser institutionsUser = new InstitutionsUser(request.getId(), request.getName(), request.getEmail(), request.getPassword(), request.getUserType());
        }

        String id = userService.join(user);
        return new Result<>("200", "회원 가입이 완료되었습니다.", id);

    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginUserRequest request) {

        userService.validateLogin(request.getId(), request.getPassword());
        LoginUserResponse response = new LoginUserResponse("dummyAccessToken", request.getId());

        // 로그인 로직 구현
        return new Result<>("200", "로그인에 성공하였습니다.", response);
    }

}
