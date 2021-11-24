# Statement-Share

This is a project I made to play around with Java service providers, but also 
to parse transactions from bank statements to put in budget sheets.

## Usage

Run `./gradlew clean build` to build and test the project. Fetch the jars you
need for your use case, place them in a directory and in the same location 
execute

    java -cp "./*" com.danwalkerdev.statement.App <input>

where `input` is either a file or folder containing the files you wish to
parse.

## Jars

statement-app.jar is the entry point of the program. You also need a jar
that implements a service for `ParserProvider`, and one jar for 
`MessagingProvider`. This is to parse the transactions from your particular
format (e.g. a csv provided by your bank) and to output this data (e.g. to
the console or via email). These include the classes from their respective
api projects, as the provider logic sits in one of the util classes so is
needed at runtime (maybe questionable). The app will loop through all
available statement parsers (a parser also has a file name filter) and then
loops through all available messaging providers, so you could collect all
your statements together and output them to the console and saved as a file
for example.
