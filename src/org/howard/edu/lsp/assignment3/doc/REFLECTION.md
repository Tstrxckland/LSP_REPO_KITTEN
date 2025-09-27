# Reflection (Assignment 3 vs. Assignment 2)

When I look back at my work on Assignment 2 and compare it to Assignment 3, the most obvious difference is the overall design and structure of the code. In Assignment 2, everything lived in a single file called `ETLPipeline.java`. That file contained the `main` method and all the logic for reading the CSV file, transforming the data, and writing the output. The approach worked but quickly became hard to read and maintain because one class was doing everything. In Assignment 3, I reorganized the program into multiple smaller classes and followed a more **object-oriented design**.  

## Design Differences

The Assignment 2 code was **procedural**: the entire ETL process happened in one long sequence of steps inside one file. If I wanted to change how data was read or how prices were transformed, I had to dig through one big method.

In Assignment 3, I created **three separate classes**:
- `Extractor` — only responsible for reading the CSV file and returning the data.
- `Transformer` — takes that raw data and applies all the transformations (uppercasing names, adjusting categories, adding price ranges).
- `Loader` — writes the transformed data back to a new CSV file with the correct headers.

Finally, `ETLMain` acts as the **entry point**, coordinating those classes and making the process easier to understand. This separation of responsibilities improved clarity and maintainability.

## Object-Oriented Principles

Assignment 3 is more **object-oriented** because it uses core OOP ideas:

- **Objects & Classes:** Each major task (extract, transform, load) is its own class with methods and internal logic. I created actual objects — an `Extractor`, a `Transformer`, and a `Loader` — instead of just calling static utility methods.
- **Encapsulation:** Each class hides its details. For example, the `Transformer` hides how it calculates price ranges and discounts. The `ETLMain` class doesn’t need to know those details; it just calls `transform()`.
- **Polymorphism (basic):** While I didn’t use inheritance directly in this assignment, I structured the code so that future transformations or different types of loaders (for example, to a database instead of CSV) could be swapped out if they had the same method signatures.
- **Modularity:** Because the program is broken into independent parts, each piece can be maintained or extended without affecting the others.

## Testing & Verification

To make sure the new Assignment 3 code worked the same as Assignment 2, I **ran both implementations on the same input CSV file** (`products.csv`). Then I compared the output CSVs. Both versions produced the same transformations — names were uppercase, electronics got discounted, premium electronics were reclassified, and price ranges matched. I also checked the summary logs in the console to verify the row counts and confirm that invalid rows were skipped correctly.

When testing, I learned how to compile and run the program from the terminal using `javac` with the correct package structure. It took some troubleshooting to get the classpath right and to fix “Main method not found” errors. Once I fixed the package names and method definitions (like `extract` and `load`), the program compiled and ran successfully.

## Reflection on the Process

This refactor made me think more like a software engineer instead of just a script writer. Splitting the code into multiple classes forced me to think about **responsibility and clean design**. The project also taught me how to structure folders and packages properly and how to run Java projects outside an IDE. Now the code is easier to maintain and extend — if I wanted to add new transformations or output formats, I could do that without rewriting everything.

Overall, Assignment 3 feels a lot cleaner and more organized than Assignment 2. The code actually makes sense to me now — it’s split into smaller parts, easier to read, and way easier to fix if something goes wrong. I can tell I’ve gotten better at thinking about object-oriented programming because I’m using classes and methods in a smarter way instead of just throwing everything into one file. This version feels more like something a real developer would write, and it makes me feel more confident about working with Java.
