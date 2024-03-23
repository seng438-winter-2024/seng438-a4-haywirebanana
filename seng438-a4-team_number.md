**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#4 – Mutation Testing and Web app testing**

| Group \#:     |     |
| -------------- | --- |
| Student Names: |     |
|             Ryan Khryss Obiar   |
|              Armin Sandhu  |
|               Parsa Karagari |
|Jayden Mikuclcik|


GOOGLE DOC LINK: https://docs.google.com/document/d/1Ejlfq_pzokh5NI4Pu-41QFGUK5ZTo1Xeosmyubp2cH4/edit 



# Introduction

For this assignment our group is tasked to dive into both Mutation testing and GUI Testing, both of which are essential practices to understand in order to have a proper grasp on the Software testing that gets applied in industry. For the first part being mutation testing we will be installing a tool to help us increase the mutilation score of the two classes we have been working with thus far in the class (Range.java and DataUtilities.java) in order to have a better understanding on the complexities of mutation testing. The end goal of this half of the lab is to design new tests to increase our mutation score by at least 10 percent. The latter part of this lab focuses on GUI testing allowing us to become familiar with a powerful tool in selenium used for GUI testing. Each of us will design two tests for websites and using selenium will automate these tests. Overall this assignment will  be used as a great introduction to the application of both Mutation testing and GUI Testing and is essential in coming up with a sense of familiarity towards these two methods.

# Analysis of 10 Mutants of the Range class 

Please Refer to the Google Docs https://docs.google.com/document/d/1Ejlfq_pzokh5NI4Pu-41QFGUK5ZTo1Xeosmyubp2cH4/edit?usp=sharing 
# Report all the statistics and the mutation score for each test class

Please Refer to the Google Docs https://docs.google.com/document/d/1Ejlfq_pzokh5NI4Pu-41QFGUK5ZTo1Xeosmyubp2cH4/edit?usp=sharing

# Analysis drawn on the effectiveness of each of the test classes

Please Refer to the Google Docs https://docs.google.com/document/d/1Ejlfq_pzokh5NI4Pu-41QFGUK5ZTo1Xeosmyubp2cH4/edit?usp=sharing

# A discussion on the effect of equivalent mutants on mutation score accuracy
The effect of equivalent mutants on a mutation score is one of inflation or deflation and if not addressed gives a false sense of security to the actual quality of the codebase. Having equivalent mutants gives weight to unimportant data in the mutation test suite and skews our perception of the code quality based on the returned mutation coverage score. This is problematic as it can allow us as software engineers to dismiss underlying issues in our code by the perception added by mutation score accuracy. Conversely this can also work as a deflator to the test score if the mutation tests we have not identified and removed fail. This is also problematic as it is a time sink for resources to be changed that don’t need to be changed. Overall the effect of equivalent mutants skews our perception of the accuracy to a number that might not be accurate to the code quality and needs to be resolved so as developers we can fully understand the mutation accuracy of our codebase.


# A discussion of what could have been done to improve the mutation score of the test suites
The only way to increase the mutation score of a mutation test is to reduce the amount of Survived tests in the mutation test suite. Some common techniques used in industry and that we used in this assignment is to Increase the test coverage and analyze the survived tests returned by the mutation testing tool. In increasing the test coverage we ensure a wider range of edge cases are covered within our tests. This allows for a greater mutation score as the likelihood that problems within the mutation tests will be resolved increases. This increase in probability directly correlates to the amount of lack of pit mutations surviving. This correlation leads to the increase in mutation score we desire. After increasing test coverage the next step is to look at each surviving log individually and decipher what needs to be added to satisfy the specific PIT mutation. By looking specifically at the Survived mutations it allows a greater understanding of the shortcomings of the codespace being tested and the ability to create niche resolutions to Survived tests that would be improbable to solve by simply increasing testing coverage. To conclude the only way we can improve mutation score is by getting a greater understanding of the test suite either by adding coverage to untested code or by deciphering the shortcomings of our Pit tests subsequently resolving the Survived test and increasing mutation score.



# Why do we need mutation testing? Advantages and disadvantages of mutation testing
Mutation testing is needed as it is essential in the Software Testing life cycle and is needed to uncover flaws in both the test suite and the source code itself. This is seen in the advantages of mutation testing as it ensures that weak tests and code gets identified and fixed. However one of the Disadvantages we saw with the mutation testing is the time-consuming nature of the testing as it would take upwards of 2:30 minutes to completely finish the mutation testing. To conclude although mutation testing is costly in both time and resources it allows for more confidence to be put into the test suite and source code effectiveness. 


# Explain your SELENUIM test case design process
Using assertions and checkpoints (verification) was essential in our case to make sure we are verifying the right functionality we expect when dealing with the website (which in our case was EBay.ca). We used verification to ensure the correct behaviour was being exhibited by the website when doing different testing of the multiple functionalities. Not only is this good for error detection as it tells us that unelected behaviour is occurring, it is also quite useful for debugging purposes it tells you what exactly is failing as in it tell you if the verification is failing and you can use any method you choose to fix that failure and ultimately debug your system. Essentially, it validates behaviour, detects errors that may be present in the SUT and finally, it helps a user debug the system and pinpoint the exact location of the error and what exactly the error is.

# Explain the use of assertions and checkpoints
The functionalities we decided to test were login, logout, my account, the search bar, the wishlist, result filtering, check out and adding to a users cart. For each functionality we decided to go through what was expected and that is how we came up with the data to be tested. For example, when testing login we saw that the login function took a password and the username and to come up with some data we decided to test invalid, passwords and invalid, usernames, as well as valid passwords and valid usernames. Another example is the searching function in the eBay website. The search function takes in a query and displays the results of that given query. For example, some queries we thought we could test were valid queries, empty query and queries with random characters. This would show how the website would react to valid data, invalid data, and an edge case with no data. We tested the cart function by adding to the cart, and seeing how the cart would react when we save an item for later, when you were signed in versus when you weren’t signed in. We also checked out the different ways to navigate to check out to test this functionality, i.e. through the cart or directly through the item page. Also for the checkout page, we input invalid coupon codes and saw the website reacted to that. For the filtering results, we put in a valid result and tested out the different filtering conditions being whether the item is brand new, which location the item should be from and we also tried adding and deleting multiple filters, and seeing what they would do to the results. We also tested the wish list by adding items to the wish list in different ways, i.e. through the homepage, or directly through the item page. We also tested removing items from the wishlist and seeing if that was done successfully. The last functionality we tested was the user profile. We tested navigating to the profile, and editing the profile. Again, we really tried to test how the website would react to invalid data or unexpected data and that was how we decided to plan out our test cases.

# how did you test each functionaity with different test data
The functionalities we decided to test were login, logout, my account, the search bar, the wishlist, result filtering, check out and adding to a users cart. For each functionality we decided to go through what was expected and that is how we came up with the data was to be tested. For example, when testing login we saw that the login function took a password and the username and to come up with some data we decided to test invalid, passwords and invalid, usernames, as well as valid passwords and valid usernames. Another example is the searching function in the eBay website. The search function takes in a query and displays the results of that given query. For example, some queries, we thought we could test were valid queries, empty query and queries with random characters. This would show how the website would react to valid data, invalid data, and and edge case with no data. We tested the heart function by adding to the cart from the cart, and seeing how the cart would react when he saves an item for later, when you were signed in versus when you weren’t signed in. Or check out the different ways to navigate to check out i.e. through the cards or directly through the item page and we also input invalid coupon codes and saw the website reacted to that. For result, filtering put in a valid result and tested out the different filtering conditions being whether the item is brand new, which location the item should be from and we also tried adding deleting multiple filters, and seeing what they would do to the results. We also tested the wish list by adding items to the wish list in different ways, i.e. through the homepage, or directly through the item, page. We also tested removing items from the wishlist and seeing if that was done successfully. I lost functionality we tested was the user profile. We tested navigating to the profile, and editing the profile. Again, we really tried to test how the website would react to invalid data or unexpected data and that was how we decided to plan out our test cases.

# Discuss advantages and disadvantages of Selenium vs. Sikulix

Please Refer to the Google Docs https://docs.google.com/document/d/1Ejlfq_pzokh5NI4Pu-41QFGUK5ZTo1Xeosmyubp2cH4/edit?usp=sharing

# How the team work/effort was divided and managed
Briefly looking at the whole assignment and from small discussions with our peers, PIT testing would take a way longer time to figure out and run compared to that of the GUI testing. We decided to split up in a group of three for PIT while the other figured out GUI testing. Having three different laptops working on different aspects allowed us to compare our results and new improved testing much more efficiently even though each time we ran PIT testing, it took us almost 20 minutes. Through this we were able to compare our results in a timely fashion as this split allowed us all to finish around the same spot. After comparing and allowing other members to try out the other half they weren’t able to complete, it helped everyone in the group to understand each part of this lab.



# Difficulties encountered, challenges overcome, and lessons learned
Some difficulties we encountered within this lab was the setup and execution of the PIT mutation tool. These difficulties stemmed from the setup of the project as initially our test cases were not compatible with the tool and did not execute. Until we realized that all tests need to be a green suite, meaning that all tests will pass, no failures and no errors. Doing this was a long process due to the PIT testing almost taking 20 minutes for some of our lab members. 

Another difficulty we encountered during this lab was creating test cases that improve our mutation coverage. This was difficult as the new test cases only improved our method by one or 2 percent and then we’d have to try and run the PIT testing for around ~15 once again to see if there are any results. This just took time and the lesson we learned from this is that we need to be efficient with our testing and realize how expensive testing can really be in a professional/production realm.



# Comments/feedback on the lab itself
Overall feedback on this lab was very positive as we got to use and understand a technology used in the field. This coupled with the critical thinking it took to come up with each method we used meant to our group a lab that is essential to complete in order to round out our skills as software engineers. The general consensus of our group was that the lab was very well designed from the content we had to implement to the workflow of the readme being laid out very well allowing for a great lab experience
