package com.example.dataproduce;


import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.util.*;

@SpringBootApplication
public class DataProduceApplication {

    public static String nName() {
        List<String> 성 = Arrays.asList("김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", "한", "오", "서", "신", "권", "황", "안",
                "송", "류", "전", "홍", "고", "문", "양", "손", "배", "조", "백", "허", "유", "남", "심", "노", "정", "하", "곽", "성", "차", "주",
                "우", "구", "신", "임", "나", "전", "민", "유", "진", "지", "엄", "채", "원", "천", "방", "공", "강", "현", "함", "변", "염", "양",
                "변", "여", "추", "노", "도", "소", "신", "석", "선", "설", "마", "길", "주", "연", "방", "위", "표", "명", "기", "반", "왕", "금",
                "옥", "육", "인", "맹", "제", "모", "장", "남", "탁", "국", "여", "진", "어", "은", "편", "구", "용");
        List<String> 이름 = Arrays.asList("가", "강", "건", "경", "고", "관", "광", "구", "규", "근", "기", "길", "나", "남", "노", "누", "다",
                "단", "달", "담", "대", "덕", "도", "동", "두", "라", "래", "로", "루", "리", "마", "만", "명", "무", "문", "미", "민", "바", "박",
                "백", "범", "별", "병", "보", "빛", "사", "산", "상", "새", "서", "석", "선", "설", "섭", "성", "세", "소", "솔", "수", "숙", "순",
                "숭", "슬", "승", "시", "신", "아", "안", "애", "엄", "여", "연", "영", "예", "오", "옥", "완", "요", "용", "우", "원", "월", "위",
                "유", "윤", "율", "으", "은", "의", "이", "익", "인", "일", "잎", "자", "잔", "장", "재", "전", "정", "제", "조", "종", "주", "준",
                "중", "지", "진", "찬", "창", "채", "천", "철", "초", "춘", "충", "치", "탐", "태", "택", "판", "하", "한", "해", "혁", "현", "형",
                "혜", "호", "홍", "화", "환", "회", "효", "훈", "휘", "희", "운", "모", "배", "부", "림", "봉", "혼", "황", "량", "린", "을", "비",
                "솜", "공", "면", "탁", "온", "디", "항", "후", "려", "균", "묵", "송", "욱", "휴", "언", "령", "섬", "들", "견", "추", "걸", "삼",
                "열", "웅", "분", "변", "양", "출", "타", "흥", "겸", "곤", "번", "식", "란", "더", "손", "술", "훔", "반", "빈", "실", "직", "흠",
                "흔", "악", "람", "뜸", "권", "복", "심", "헌", "엽", "학", "개", "롱", "평", "늘", "늬", "랑", "얀", "향", "울", "련");
        Collections.shuffle(성);
        Collections.shuffle(이름);
        return 성.get(0) + 이름.get(0) + 이름.get(1);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Connection con = null;                 // 데이터 베이스와 연결을 위한 객체
        Statement stmt = null;                 // SQL 문을 데이터베이스에 보내기위한 객체
        ResultSet rs = null;                   // SQL 질의에 의해 생성된 테이블을 저장하는 객체

        // 1. JDBC Driver Class - com.mysql.jdbc.Driver
        String driver = "com.mysql.cj.jdbc.Driver";

        // 2. 데이터베이스에 연결하기 위한 정보
        String url = "jdbc:mysql://localhost:3306/data";       // 연결문자열
        String user = "root";                                     // 데이터베이스 ID
        String pw = "1234";                                       // 데이터베이스 PW


        for (; ; ) {
            System.out.println();
            System.out.println();
            System.out.println("무작위 이름 생성 프로그램");
            System.out.println();
            System.out.println("1번 = 테이블 생성, 2번 = 테이블 데이터 등록, 3번 = 데이터 조회, 4번 = 어떤 테이블이 있는지 확인 (1,2,3,4 제외) = 프로그램 종료 ");
            String menu = sc.next();

            switch (menu) {
                case "1":
                    System.out.println("1번 : 테이블 생성");
                    System.out.println();
                    try {
                        System.out.print("테이블 몇개 만들건지 입력 : ");
                        int tableCount = sc.nextInt();         //tableCount == 테이블 개수이자 밑에 for문 횟수 결정
                        System.out.println("테이블 개수 = " + tableCount + "개");


                        for (int i = 0; i < tableCount; i++) {
                            System.out.print("테이블 이름 입력 : ");
                            String tableName = sc.next();      //테이블 이름
                            System.out.println("테이블 이름 = " + tableName);


                            System.out.println("몇가지 컬럼을 쓸것인지");
                            int columnCount = sc.nextInt();
                            System.out.println("컬럼 갯수 = " + columnCount + "개");
                            System.out.println("컬럼 이름 과 데이터 타입 입력(   숫자면 int, 글자면 char(10)   ) = ");
                            List<String> columnName = new ArrayList<>();
                            String name;
                            String type;
                            for (int j = 0; j < columnCount; j++) {
                                name = sc.next();
                                columnName.add(name);
                                type = sc.next();
                                if (type.equals("int")) {
                                    type = "int AUTO_INCREMENT";
                                    columnName.add(type);
                                } else {
                                    columnName.add(type);
                                }
                                columnName.add(",");
                            }
                            //    System.out.println(name);
                            //    System.out.println(type);
                            //    String columnName= name + " " +data +",";
                            //    System.out.println(columnName);
                            StringBuilder cN = new StringBuilder();     //list 출력하면 [~~, ~~]  나오는걸 대괄호를 없애줌
                            for (String column : columnName) {
                                cN.append(column);
                                cN.append(" ");
                            }


                            try {
                                Class.forName(driver);
                                con = DriverManager.getConnection(url, user, pw); // DB 연결
                                // 4. SQL 문장을 실행하고 결과를 리턴
                                // stmt.excuteQuery(SQL) : select
                                // stmt.excuteUpdate(SQL) : insert, update, delete ..
                                String sql = "create table " + tableName + "(" + cN + "primary key (" + columnName.get(0) + ") )";
                                System.out.println(sql);
                                stmt = con.createStatement();
                                stmt.execute(sql);
                            } catch (SQLException e) {

                                System.out.println("SQL Error : " + e.getMessage());
                                System.out.println();
                                System.out.println("컬럼 등록을 바르게 입력해야됩니다.");
                                System.out.println();
                                break;
                            } catch (ClassNotFoundException e1) {
                                System.out.println("[JDBC Connector Driver 오류 : " + e1.getMessage() + "]");
                                break;
                            }
                            System.out.println("테이블 생성");
                            System.out.println("테이블 이름: " + tableName + " " + "컬럼: " + cN);
                        }
                    } catch (InputMismatchException e2) {
                        System.out.println("타입을 바르게 입력해야됩니다.");
                        break;
                    }
                    continue;


                //테이블에 데이터 삽입
                case "2":
                    System.out.println("2번: 테이블 데이터 등록");
                    System.out.println();
                    System.out.println("어떤 테이블에 넣을것인지: ");
//        String ISQL = sc.nextLine();
//        String InsertSQL = ISQL;
                    String whatTable = sc.next();
                    System.out.println(whatTable + " 테이블");
                    System.out.println("몇명의 이름을 등록하는지 입력: ");
                    int nameCount = sc.nextInt();
                    System.out.println(nameCount + "명");
                    //insert
                    try {
                        //드라이버 로딩
                        Class.forName(driver);
                        //db연결
                        con = DriverManager.getConnection(url, user, pw); // DB 연결
                        //SQL문
                        // System.out.println(nameCount);      //몇명인지
                        for (int i = 0; i < nameCount; i++) {
                            String sql = "INSERT INTO " + whatTable + " (name) " + " VALUES " + " ('" + nName() + " ')";

                            stmt = con.createStatement();
                            stmt.execute(sql);
                        }
                    } catch (SQLException e) {

                        System.out.println("SQL Error : " + e.getMessage());
                        break;
                    } catch (ClassNotFoundException e1) {

                        System.out.println("[JDBC Connector Driver 오류 : " + e1.getMessage() + "]");
                        break;
                    }

                    System.out.println(nameCount + "명의 이름을 테이블에 등록");
                    System.out.println();
                    //System.out.println("테이블 이름: " + tableName + " " + "컬럼: " + cN);

                    continue;

                case "3":
                    System.out.println("3번 : 데이터 조회");
                    System.out.println();
                    System.out.println("어떤 테이블의 데이터를 조회 할것인가요.");
                    String selectTable = sc.next();
                    System.out.println("선택한 테이블 : " + selectTable);
                    System.out.println("몇명의 이름을 얻고싶나요.");
                    int getNames = sc.nextInt();
                    System.out.println("얻고자하는 이름 수 : " + getNames);
                    System.out.println();

                    //select
                    try {
                        // 1. JDBC 드라이버 로딩
                        Class.forName(driver);

                        // 2. Connection 객체 생성
                        con = DriverManager.getConnection(url, user, pw); // DB 연결

                        // 3. Statement 객체 생성
                        String sql = "SELECT * FROM  " + selectTable + " WHERE num <= " + getNames;
                        stmt = con.createStatement();
                        // stmt.execute(sql);
                        // 4. SQL 문장을 실행하고 결과를 리턴
                        // stmt.excuteQuery(SQL) : select
                        // stmt.excuteUpdate(SQL) : insert, update, delete ..
                        rs = stmt.executeQuery(sql);
                        // 5. ResultSet에 저장된 데이터 얻기 - 결과가 2개 이상
                        System.out.println("번호         이름");
                        while (rs.next()) {

                            String num = rs.getString("num");
                            String name = rs.getString("name");


                            System.out.println(num + "           " + name);
                        }

                        //5. ResultSet에 저장된 데이터 얻기 - 결과가 1개
                        // if(rs.next()) {
                        //
                        // }
                        // else {
                        //
                        // }
                    } catch (SQLException e) {

                        System.out.println("SQL Error : " + e.getMessage());

                    } catch (ClassNotFoundException e1) {

                        System.out.println("[JDBC Connector Driver 오류 : " + e1.getMessage() + "]");

                    } finally {

                        //사용순서와 반대로 close 함
                        if (rs != null) {
                            try {
                                rs.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        if (stmt != null) {
                            try {
                                stmt.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        if (con != null) {
                            try {
                                con.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    continue;




                case "4":
                    System.out.println("4번 : 테이블 확인");
                    System.out.println();

                    //insert
                    try {
                        //드라이버 로딩
                        Class.forName(driver);
                        //db연결
                        con = DriverManager.getConnection(url, user, pw); // DB 연결
                        //SQL문
                        // System.out.println(nameCount);      //몇명인지
                            String sql = "SHOW TABLES";

                            stmt = con.createStatement();
                            stmt.execute(sql);
                        rs = stmt.executeQuery(sql);
                        while (rs.next()) {

                            String Tables = rs.getString(1);

                            System.out.println(Tables);
                        }
                    } catch (SQLException e) {

                        System.out.println("SQL Error : " + e.getMessage());
                        break;
                    } catch (ClassNotFoundException e1) {

                        System.out.println("[JDBC Connector Driver 오류 : " + e1.getMessage() + "]");
                        break;
                    }
                    continue;

                default:
                    System.out.println("프로그램에 존재하지 않는 목록입니다.");
                    break;

            }
          if(menu != "1"){
                break;
          } else if(menu != "2") {
              break;
          }else if(menu != "3"){
              break;
          }
        }
    }
}