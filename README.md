# Reviews API
Opinions. You know the saying...everyone has one. And the Internet has made them easier to consume, or ignore.

Acquisitions, Inc. has many, many products from its years of, well, acquiring things. They have asked you to build a reviews API for some of their current products. The data gathered will help them gain insight into their customer base and the products they have come to enjoy...or enjoy less.

Acquisitions, Inc. requires:

- Appropriate use of Git and access to your application on GitHub
- Useful instructions in the form of a README.md file
- Ability for Users to perform the following functions using a REST Client:
  - View review by Id.
  - View reviews by Category
  - Add new reviews.
  - Assign hashtags to reviews.
  - Create new hashtags.
  - View all reviews associated with a hashtag.
- The view will be the REST Client.
- You choose what it is you’ll be reviewing

## Entities

- Review
  - Decide on what type of product you’ll be reviewing.
  - Each review should belong to a single category.
  - A review can have any number of hashtags associated with it
  - Should have an author field
  - Should have a field to hold the text of the review
  - Should have some kind of rating system. For instance, a 5 star rating.
- Category
  - Based on the product you choose, should be a category of that product. For instance, if you chose music, a category could be Classic Rock.
  - Categories can be assigned to any number of reviews.
- Hashtag
  - Should describe a product in a few words or less, like Hashtags on social media.
  - A hashtag can have any number of reviews assigned to it.
