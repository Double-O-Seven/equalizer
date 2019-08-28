/*
 * Copyright (C) 2019 Adrian-Philipp Leuenberger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ch.leadrian.equalizer.samples;

import ch.leadrian.equalizer.Equals;
import ch.leadrian.equalizer.HashCode;

public class Book {

    private static final Equals<Book> EQUALS = Equals.of(Book.class, Book::getTitle, Book::getAuthor, Book::getIsbn);

    private static final HashCode<Book> HASH_CODE = HashCode.of(Book::getTitle, Book::getAuthor, Book::getIsbn);

    private final String title;
    private final Person author;
    private final String isbn;

    public Book(String title, Person author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public Person getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public boolean equals(Object obj) {
        return EQUALS.equals(this, obj);
    }

    @Override
    public int hashCode() {
        return HASH_CODE.hashCode(this);
    }
}
