package pt.ulisboa.tecnico.cmov.librarist.caches;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pt.ulisboa.tecnico.cmov.librarist.models.Book;
import pt.ulisboa.tecnico.cmov.librarist.models.Library;

public class LibraryCache {

    // List of libraries
    private HashMap<Integer, Library> libraries = new HashMap<>();


    public void addLibrary(int libId, Library library){
        libraries.put(libId, library);

        // TODO add all the books that this library has and add them to the BookCache
    }

    public Library getLibrary(int libId){
        return libraries.get(libId);
    }

    public List<Library> getLibraries(){
        List<Library> allLibraries = new ArrayList<>();
        for (Library library : libraries.values()){
            allLibraries.add(library);
        }
        return allLibraries;
    }

    public List<Book> getBooksFromLibrary(int libraryID, BookCache bookCache){
        // Find Library
        Library library = null;
        for (Library lib : libraries.values()){
            if (lib.getId() == libraryID){
                library = lib;
            }
        }
        // Library is not in cache / does not exist
        if (library == null){ return null; }

        // Get books for the library
        List<Book> books = new ArrayList<>();
        for (int bookId : library.getBookIds()) {
            Book book = bookCache.getBook(bookId);
            books.add(book);
        }
        return books;
    }
}
