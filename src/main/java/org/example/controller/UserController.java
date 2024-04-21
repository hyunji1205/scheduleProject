package org.example.controller;

import org.example.Container;
import org.example.dto.User;

import java.util.List;
import java.util.Scanner;

public class UserController extends Controller {

    private Scanner sc;
    private List<User> users;
    private String cmd;
    private String actionMethodName;



    public UserController(Scanner sc) {
        this.sc = sc;
        users = Container.userDao.users;
    }

    public void doAction(String cmd, String actionMethodName) {
        this.cmd = cmd;
        this.actionMethodName = actionMethodName;

        switch (actionMethodName) {
            case "가입":
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
    public void makeTestData() {
        System.out.println("테스트를 위한 회원 데이터를 생성합니다.");

        users.add(new User("애송이", "111"));
        users.add(new User("송현지", "222"));
        users.add(new User("박재민", "333"));
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

        User user = new User(loginName, loginPw);
        users.add(user);

        System.out.printf("회원가입이 완료되었습니다! %s님 환영합니다!\n", loginName);

    }

    public void doLogin() {

        System.out.printf("이름 : ");
        String loginName = sc.nextLine();
        System.out.printf("비밀번호 : ");
        String loginPw = sc.nextLine();

        User user = getUserByLoginName(loginName);

        if ( user == null ) {
            System.out.println("해당회원은 존재하지 않습니다.");
            return;
        }

        if ( user.loginPw.equals(loginPw) == false ) {
            System.out.println("비밀번호가 일치하지 않습니다.");
            return;
        }

        loginedUser = user;
        System.out.printf("로그인 성공! %s님 환영합니다!\n", loginName);
    }


    private void doLogout() {
        loginedUser = null;
        System.out.println("로그아웃 되었습니다.");
    }

    private boolean isJoinableLoginName(String loginName) {
        int index = getUserIndexByLoginName(loginName);

        if ( index == -1 ) {
            return true;
        }

        return false;
    }

    private int getUserIndexByLoginName(String loginName) {
        int i = 0;

        for ( User user : users ) {
            if ( user.loginName.equals(loginName) ) {
                return i;
            }
            i++;
        }

        return -1;
    }

    private User getUserByLoginName(String loginName) {
        int index = getUserIndexByLoginName(loginName);

        if ( index == -1 ) {
            return null;
        }

        return users.get(index);
    }
}
