package pt.ulisboa.tecnico.cmov.librarist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.ViewCompat;

import java.util.List;

import pt.ulisboa.tecnico.cmov.librarist.models.Book;

public class BookMenuActivity extends AppCompatActivity {

    private static final String BOOK_ID_MESSAGE = "bookId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_books);
        Log.d("BookMenuActivity", "loaded layout");

        // TODO call backend to get all books
        List<Book> bookList = List.of(
                new Book(0,"img", "The Playbook", false),
                new Book(1, "img", "Little Women", true));
        addBookItemsToView(bookList);

        // Set up onclick method for the search button
        setupSearchButton();

        // Back Button
        setupBackButton();
    }


    /** -----------------------------------------------------------------------------
     *                                  BUTTONS FUNCTIONS
     -------------------------------------------------------------------------------- */

    private void setupSearchButton() {
        ImageButton searchBtn = findViewById(R.id.search_book_btn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textFilter = (TextView) findViewById(R.id.book_title_input);
                String filter = (String) textFilter.getText();
                // TODO call backend to get filtered books; call addBookItemsToView
            }
        });
    }

    private void setupBackButton(){
        ImageView back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    /** -----------------------------------------------------------------------------
     *                                  OTHER FUNCTIONS
     -------------------------------------------------------------------------------- */

    private void setupBookCard(CardView cardView) {
        cardView.setOnClickListener(v -> {
            int bookId = Integer.parseInt(v.getTag().toString());

            Intent intent = new Intent(BookMenuActivity.this, BookInfoActivity.class);
            intent.putExtra(BOOK_ID_MESSAGE, bookId);
            startActivity(intent);
        });
    }
    private void addBookItemsToView(List<Book> books) {
        LinearLayout parent = findViewById(R.id.div_books_list);
        LayoutInflater inflater = getLayoutInflater();

        books.forEach(book -> {
            // Create new element for the book
            CardView child = (CardView) inflater.inflate(R.layout.book_menu_item, null);
            // Set text to the book title
            TextView cardText = (TextView) child.getChildAt(1);
            cardText.setText(book.getTitle());
            child.setTag(String.valueOf(book.getId()));
            setupBookCard(child);

            parent.addView(child);
        });
    }
}
