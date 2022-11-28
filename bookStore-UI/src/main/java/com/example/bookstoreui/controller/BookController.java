package com.example.bookstoreui.controller;

import com.example.bookstoreui.ds.AccountInfo;
import com.example.bookstoreui.ds.Book;
import com.example.bookstoreui.ds.Cart;
import com.example.bookstoreui.ds.TransportInfo;
import com.example.bookstoreui.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Random;
import java.util.Set;

@Controller
@RequestMapping("/bookstore")
public class BookController {

    private RestTemplate restTemplate=new RestTemplate();

    @Value("${payment.url}")
    private String paymentUrl;

    @Value("${transport.url}")
    private String transportUrl;

    @Autowired
    private BookService bookService;
    @Autowired
    private Cart cart;

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        bookService.findAllBooks()
                .forEach(System.out::println);

        return "success!";
    }


    @GetMapping({"/", "/home", "/index"})
    public String index(Model model) {
        model.addAttribute("success",model.containsAttribute("success"));
        model.addAttribute("books", bookService.findAllBooks());
        return "home";
    }

    @GetMapping("/book/{id}")
    public String detailBooks(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.findBooksByID(id));
        return "book-details";
    }

    @GetMapping("/book/cart/{id}")
    public String addToCart(@PathVariable("id") int id) {
        cart.addToCart(bookService.findBooksByID(id));
        return "redirect:/bookstore/book/" + id;
    }

    @ModelAttribute("cartSize")
    public int cartSize() {
        return cart.cartSize();
    }



    @GetMapping("/book/cart/view")
    public String viewCart(@ModelAttribute Book book){
        return "cart-view";
    }

    @GetMapping("/book/delete/{id}")
    public String deleteBookFromCart(@PathVariable("id")int id){
        cart.removeBookFromCart(bookService.findBooksByID(id));

        return "redirect:/bookstore/book/cart/view";
    }

    @GetMapping("/book/cart/clear")
    public String clearCart(){
        cart.clearCart();
        return "redirect:/bookstore/book/cart/view";
    }

    @PostMapping("/book/checkout")
    public String handleCheckout(@ModelAttribute Book book){
        Set<Book> bookSet=cart.getBookSet();
        int i=0;
        for (Book book1:bookSet){
            book1.setItemCount(book.getQuantity().get(i));
            i++;
        }
        cart.setBookSet(bookSet);
//        cart.getBookSet().forEach(System.out::println);
//        ResponseEntity response =restTemplate.postForEntity(paymentUrl+"payment/checkout/1",String.valueOf(totalPrice()),String.class);
//        System.out.println("====="+response.getStatusCode());
        return "redirect:/bookstore/account-info";
    }

    @GetMapping("/account-info")
    public String paymentInfoForm(Model model){
        model.addAttribute("accountInfo",new AccountInfo());
        return "accountInfo-form";
    }

    @PostMapping("/account-info")
    public String processAccountInfo(AccountInfo accountInfo, BindingResult result, @ModelAttribute("books") Set<Book> books, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "accountInfo-form";
        }
        AccountInfo accountInfo1=new AccountInfo();
        accountInfo1.setAccountNumber(accountInfo.getAccountNumber());
        accountInfo1.setName(accountInfo.getName());
        accountInfo1.setTotalAmount(totalPrice());
        ResponseEntity paymentResponse=restTemplate.postForEntity(paymentUrl+"payment/checkout",accountInfo1, String.class);
        System.out.println("====="+paymentResponse.getStatusCode());
//        var transportInfo=new TransportInfo(accountInfo.getName(),generateOrderID(),books); ****record way***

        if (paymentResponse.getStatusCode().equals(HttpStatus.CREATED)){
            var transportInfo=new TransportInfo(accountInfo.getName(),generateOrderID(),books,totalPrice());
            ResponseEntity transportResponse=
                    restTemplate.postForEntity(transportUrl+"transport/transport-create",transportInfo,String.class);
            if(transportResponse.getStatusCode().equals(HttpStatus.CREATED)){
                redirectAttributes.addFlashAttribute("success",true);
            }
        }else {
            throw new RuntimeException("Checkout Error");
        }
        cart.clearCart();
        return "redirect:/bookstore/home";
    }

    record TransportInfo2(String name,String orderId,Set<Book> books){}

    private String generateOrderID(){
        return "AMZ000"+ new Random().nextInt(10000)+10000;
    }

    private double totalPrice() {
        return cart.getBookSet().stream()
                        .map(b->b.getItemCount()*b.getPrice()).mapToDouble(d->d).sum();
    }

    @ModelAttribute("books")
    public Set<Book> getAllBooksFromCart(){
        return cart.getBookSet();
    }


}
