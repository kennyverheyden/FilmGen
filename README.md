 <h1>Film gen</h1>

<h3>The idea</h3>

The idea of this program is that it generates the title and description of a movie by randomly grabbing words from a database.

This idea is based on sakhila_master's database, in which the movie table shows all generated movies instead of actual movies.

<h3>How will it work</h3>

A title and description is created by choosing random words from a database. That can be just a word, a name of a place, etcetera…

The title and description consists of a template and the database chooses words from other tables.

Title:

<code>word + word2</code>

Description:

<code>“(A/An) (hyperbolic) (story) of (a/an) (subject) and (a/an) (subject) who must (verb) (a/an) (subject) in (location)” </code>

The indefinite article is dynamic of the next word. 

<h3>Database</h3>

All generated titles & descriptions are stored in a relational database with foreign keys.

The database is used in SQLite.
