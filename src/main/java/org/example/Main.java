package org.example;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Motivation> motivations = new ArrayList<Motivation>();
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시 mm분 ss초");
        Scanner sc = new Scanner(System.in);

        int lastId = 0;
        System.out.println("==명언 앱 실행==");
        while (true) {

            System.out.print("명령어) ");
            String cmd = sc.nextLine();
            if(cmd.equals("등록")) {
                int id= lastId+1;
                String regDate = now.format(formatter);
                System.out.print("명언 :");
                String content = sc.nextLine();
                System.out.print("작가 :");
                String author = sc.nextLine();
                System.out.println(id+"번 명언이 등록되었습니다.");
                motivations.add(new Motivation(id, regDate, content, author));
                lastId++;
            }
            else if(cmd.equals("목록")){
                System.out.println("   번호    /    작가    /    명언     \n");
                System.out.println("=".repeat(40));
                for(int i=motivations.size()-1; i>=0; i--){
                    System.out.printf("    %d    /    %s    /    %s\n",motivations.get(i).getId(),motivations.get(i).getAuthor(),motivations.get(i).getContent());
                }

            }
            else if(cmd.startsWith("삭제?id=")){
                int id = Integer.parseInt(cmd.split("=")[1]);
                for(int i=0; i<=motivations.size()-1; i++){
                    if(motivations.get(i).getId() == id){
                        System.out.println(id+"번 명언이 삭제되었습니다.");
                        motivations.remove(i);
                        break;
                    }
                    if(motivations.get(i).getId() != id)
                        System.out.println(id+"번 명언은 존재하지 않습니다.");
                }
            }
            else if(cmd.startsWith("수정?id=")){
                int id = Integer.parseInt(cmd.split("=")[1]);
                String regDate = now.format(formatter);
                System.out.println("명언(기존) : "+motivations.get(id-1).getContent());
                System.out.println("작가(기존) : "+motivations.get(id-1).getAuthor());
                System.out.print("명언 : ");
                String newContent = sc.nextLine().trim();
                System.out.print("작가 : ");
                String newAuthor = sc.nextLine().trim();
                for(int i=0; i<=motivations.size()-1; i++){
                    if(motivations.get(i).getId() == id){
                        System.out.println(id+"번 명언이 수정되었습니다.");
                        motivations.set(i, new Motivation(id, regDate, newContent, newAuthor));
                        break;
                    }
                    if(motivations.get(i).getId() != id)
                        System.out.println(id+"번 명언은 존재하지 않습니다.");
                }
            }
            else if(cmd.startsWith("상세보기?id=")){
                int id = Integer.parseInt(cmd.split("=")[1]);
                for(int i=0; i<=motivations.size()-1; i++){
                    if(motivations.get(i).getId() == id){
                        System.out.println("번호: " +id);
                        System.out.println("날짜: " +motivations.get(i).getRegDate());
                        System.out.println("작가: " +motivations.get(i).getAuthor());
                        System.out.println("내용: " +motivations.get(i).getContent());
                    }
                }
            }
        }
    }
}