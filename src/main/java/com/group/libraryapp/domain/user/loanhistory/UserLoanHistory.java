package com.group.libraryapp.domain.user.loanhistory;

import com.group.libraryapp.domain.user.User;

import javax.persistence.*;

@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    @JoinColumn(nullable = false)
    @ManyToOne      //N(UserLoanHistory):1 관계, 연관관계의 주인임
    private User user;
    private String bookName;
    private boolean isReturn;      //int로 해도 괜촪음

    protected UserLoanHistory(){

    }
    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false;
    }

    public void doReturn(){
        this.isReturn = true;
    }

    public String getBookName() {
        return this.bookName;
    }
}
