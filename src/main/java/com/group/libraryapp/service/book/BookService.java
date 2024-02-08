package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository,
                       UserLoanHistoryRepository userLoanHistoryRepository,
                       UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveBook(BookCreateRequest request){
        bookRepository.save(new Book(request.getName()));
    }

    @Transactional
    public void loanBook(BookLoanRequest request){
        //1.책정보를 가져온다(이름기준으로)
        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);
        //2.대출정보기록을 확인해서 대출중인지 확인한다.
        //3.만약에 확인했는데 대출중이라면 예외를 발생시킨다.
        if(userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(),false)){
            throw new IllegalArgumentException("이미 대출되어 있는 책입니다.");
        }
        //4.유저 정보를 가져온다.
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        //5.유저 정보와 책 정보를 기반으로 UserLoanHistory를 저장한다.
//        userLoanHistoryRepository.save(new UserLoanHistory(user,book.getName()));
        user.loanBook(book.getName());  //loanBook() : cascade옵션에 의해서 User와
                                        // User가 가지고 있는 리스트(userLoanHistory)안에
                                        //새로운 UserLoanHistory가 들어가게된다.
    }

    @Transactional
    public void returnBook(BookReturnRequest request) {
        //1.유저정보를 가져온다
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        //2.유저id와 주어지는 책이름을 통해서 대출기록을 찾는다.
//        UserLoanHistory history =
//                userLoanHistoryRepository.findByUserIdAndBookName(user.getId(), request.getBookName())
//                        .orElseThrow(IllegalArgumentException::new);

        //3.대출기록을 반납처리
//        history.doReturn();
//        userLoanHistoryRepository.save(history);    //생략해도된다. @Transactional 은 영속성 컨텍스트를 가지고있고, 변경감지기능이 있기에 자동으로 감지된다.
        System.out.println("Hello");
        user.returnBook(request.getBookName());



    }
}
