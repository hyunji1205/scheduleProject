package org.example.controller;

import org.example.container.Container;
import org.example.dto.User;
import org.example.service.UserService;

import java.util.Scanner;

public class UserController extends Controller {

    private Scanner sc;
    private String cmd;
    private String actionMethodName;
    private UserService userService;
    private Session session;



    public UserController(Scanner sc) {
        this.sc = sc;
        userService = Container.getUserService();
        session = Container.getSession();
    }

    public void doAction(String cmd) {
        this.cmd = cmd;
        switch (cmd) {
            case "회원가입":
                doJoin();
                break;
            case "로그인":
                doLogin();
                break;
            case "로그아웃":
                doLogout();
                break;
            default:
                System.out.println("존재하지 않는 명령어 입니다.");
                break;
        }
    }

    public void doJoin() {

        String loginName = null;

        while ( true ) {
            System.out.printf("이름 : ");
            loginName = sc.nextLine();

            if ( isJoinableLoginName(loginName) == false ) {
                System.out.printf("%s(은)는 이미 사용중인 아이디 입니다.\n", loginName);
                continue;
            }

            break;
        }

        String loginPw = null;
        String loginPwConfirm = null;

        while ( true ) {
            System.out.printf("비밀번호 : ");
            loginPw = sc.nextLine();
            System.out.printf("비밀번호 확인 : ");
            loginPwConfirm = sc.nextLine();

            if ( loginPw.equals(loginPwConfirm) == false ) {
                System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
                continue;
            }

            break;
        }

        userService.join(loginName, loginPw);

        System.out.printf("회원가입이 완료되었습니다! %s님 환영합니다!\n", loginName);

    }

    public void doLogin() {

        System.out.printf("이름 : ");
        String loginName = sc.nextLine();
        System.out.printf("비밀번호 : ");
        String loginPw = sc.nextLine();

        User user = userService.getUserByLoginName(loginName);

        if ( user == null ) {
            System.out.println("해당회원은 존재하지 않습니다.");
            return;
        }

        if ( user.loginPw.equals(loginPw) == false ) {
            System.out.println("비밀번호가 일치하지 않습니다.");
            return;
        }

        session.setLoginedUser(user);
        User setLoginedUser = session.getLoginedUser();

        System.out.printf("로그인 성공! %s님 환영합니다!\n", loginName);
    }


    private void doLogout() {
        session.setLoginedUser(null);
        System.out.println("로그아웃 되었습니다.");
    }

    private boolean isJoinableLoginName(String loginName) {
        User user = userService.getUserByLoginName(loginName);

        if ( user == null ) {
            return true;
        }

        return false;
    }


}
