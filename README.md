# Sample usage from the bash shell

* curl -X POST http://localhost:8080/categories -H 'Content-Type: application/json' -d '{"name": "nonfiction", "description": "Purports to correspond to reality"}'
* curl -X GET http://localhost:8080/categories -H 'Content-Type: application/json'
* curl -X POST http://localhost:8080/reviews -H 'Content-Type: application/json' -d '{"title": "GEB", "author": "RH", "text": "Forwards and backwards"}'
* curl -X GET http://localhost:8080/reviews -H 'Content-Type: application/json'
* curl -X GET http://localhost:8080/reviews/1 -H 'Content-Type: application/json'
* curl -X PUT http://localhost:8080/reviews/1 -H 'Content-Type: application/json' -d '{"id" : 1, "title": "GEB", "author": "RH", "text": "Pulitzer"}'
* curl -X POST http://localhost:8080/reviews/1/tags/top10 -H 'Content-Type: application/json'
* curl -X GET http://localhost:8080/tags -H 'Content-Type: application/json'
* curl -X POST http://localhost:8080/reviews/1/tags/top50 -H 'Content-Type: application/json'
* curl -X GET http://localhost:8080/tags -H 'Content-Type: application/json'
* curl -X GET http://localhost:8080/tags/top10 -H 'Content-Type: application/json'

Endpoints
* GET
  * /categories --> List all categories
  * /categories/{category_name} --> List all books for a given category
  * /reviews --> List all reviews (QUESTION: is this too much???)
  * /reviews/{review_id} --> The text of a single review
  * /reviews/{review_id}/tags --> Get the list of tags for a given review
  * /tags --> Get the list of tags
  * /tags/{tag_id} --> Get the list of reviews for a given tag_id
* POST
  * /categories --> Create a new category
  * /reviews --> Create a new review and return that review with the id field populated with the actual id.
  * /reviews/{review_id}/tags --> Add a tag to an existing review

* PUT
  * /reviews/{review_id} --> Update a review

* DELETE
  * /reviews/{review_id}/tags/{tag_id} --> Remove a tag
  * /categories/{category_name} --> Remove a category

Typical use-case:
* POST to /reviews
* POST to /reviews/1/tags/top40
* POST to /reviews/1/tags/englishfiction
* GET from /reviews/1/tags
* GET from /tags/top40 --> give you all matching reviews
* DELETE to /reviews/1/tags/englishfiction --> oh, I made a mistake



# Reviews API

Opinions. You know the saying...everyone has one. And the Internet has made them easier to consume, or ignore.

Acquisitions, Inc. has many, many products from its years of, well, acquiring things. They have asked you to build a
reviews API for some of their current products. The data gathered will help them gain insight into their customer base
and the products they have come to enjoy...or enjoy less.

Acquisitions, Inc. requires:

- Appropriate use of Git and access to your application on GitHub
- Useful instructions in the form of a README.md file
- Ability for Users to perform the following functions using a REST Client:
    - Add new reviews.
    - View review by Id.
        * GET to /reviews/{review_id} should return JSON containing the review.
        * Retrieve the review from a database!
    - Add categories using REST.
    - View categories
        * GET to /categories should return a JSON list of categories.
    - View reviews by Category
        * GET to /categories/{category_id} should return a list of review_ids for that category???
    - View all reviews associated with a hashtag.
    - Assign hashtags to reviews.
    - Create new hashtags.
- The view will be the REST Client. The site will start empty, and will be populated over time by users hitting POST
  endpoints to add reviews, etc. We don't know or care how they write a review ... we're just a place they can send them
  to over REST. So the site could be filled by people using the bash `curl -x POST` command, or some other java program
  could send POST requests to our website, etc. This is no different from a website not caring if you Firefox or Chrome
  or send requests manually.
- You choose what it is you’ll be reviewing

## Entities

- Review
    - Decide on what type of product you’ll be reviewing.
    - Each review should belong to a single category.
    - A review can have any number of hashtags associated with it
    - Should have a title field
    - Should have an author field
    - Should have a field to hold the text of the review
    - Should have some kind of rating system. For instance, a 5 star rating.
- Category
    - Based on the product you choose, should be a category of that product. For instance, if you chose music, a
      category could be Classic Rock.
    - Categories can be assigned to any number of reviews.
- Hashtag
    - Should describe a product in a few words or less, like Hashtags on social media.
    - A hashtag can have any number of reviews assigned to it.

1. First understand the Entities as plain java objects. What's final and what's not. What fields should it have. What are the relationships between fields in this class and fields in other classes?
2. Look at the requirements for REST endpoints and ensure that the entities expose the necessary behavior.
3. Figure out how to map these entities to Database tables. (HARD)
4. Link this altogether to REST endpoints (NOT HARD).
