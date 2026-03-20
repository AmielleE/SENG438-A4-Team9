**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#4 – Mutation Testing and Web app testing**

| Group \#:  9    |
| -------------- |
| Student Names: |
| Josral Frederick |
| Amielle El Makhzoumi |
| Fatma Alzubaidi | 
| Faris Janjua | 
| Erioluwa Olubadejo | 

# Introduction


# Analysis of 10 Mutants of the Range class 

# Report all the statistics and the mutation score for each test class



# Analysis drawn on the effectiveness of each of the test classes

# A discussion on the effect of equivalent mutants on mutation score accuracy

# A discussion of what could have been done to improve the mutation score of the test suites

# Why do we need mutation testing? Advantages and disadvantages of mutation testing

# Explain your SELENUIM test case design process
Our test case design process had a structured approach to ensure we had coverage, correctness, and maintainability. First, we identified the core functionalities that customers would interact with, such as adding items to the cart, switching languages, and searching for products. Next, for each functionality, we recorded user actions in Selenium IDE to capture realistic workflows.

After recording, we reviewed each test to verify that it reflected a valid use case and refined selectors to improve reliability and reduce dependency on dynamic page elements. This was done by changing xpaths to clear css/html element IDs. We incorporated multiple target selectors (CSS, XPath, and link text) for critical elements to ensure robustness across different browsers and minor UI updates.

For example, in the “viewCart” test, we opened the homepage, resized the window for consistency, clicked the cart icon, and verified the cart header was present. Similarly, in “changeLanguage,” we clicked the language toggle, selected a different language, and verified the header reflected the change. This approach allowed us to systematically design tests that mimic real user behavior while being resilient to minor UI changes.

# Explain the use of assertions and checkpoints
Assertions were important to our tests to automatically verify that the application acted as expected at each step. Every test included at least one assertion or checkpoint. For example, in “viewCart,” we used assertElementPresent to confirm that the cart header was visible after clicking the cart icon. In “filterByGrocery,” we asserted the presence of the “Fruits & vegetables” filter button to ensure the correct product category loaded. In “signInPage,” we verified that the login page header appeared after navigating through the sign-in workflow.

These automated verification points ensure that failures are caught immediately without hands-on inspection, improving test reliability and efficiency. 

For some scripts, visual verification was enough, like scrolling through product lists or hovering over menu items. In these cases, we did not include assertions because the main outcome was just a visual change or animation rather than a specific major element change. For example, when hovering over a product image to reveal a quick-view button, the main goal was to observe that the quick-view button appeared. This is only a visual check, so adding an assertion was unnecessary and would not have added meaningful verification.

# How did you test each functionality with different test data
To make sure that our tests were robust, we incorporated different input data and scenarios wherever applicable. For example, in the search and open product workflow, we tested multiple search terms like “laptop” and verified that different products could be opened and added to the cart. For store pickup, we selected different store locations to confirm that the pickup workflow functioned correctly for multiple options. The language toggle was tested with at least two language options to ensure correct localization.

For functionalities where test data variation was not applicable, like when verifying the presence of static UI elements like the cart icon, search bar, or page headers, we did not use multiple data inputs because the test goal was to confirm the existence and visibility of these elements. These elements do not change based on user input, so additional data would not provide meaningful extra verification.

# Discuss advantages and disadvantages of Selenium vs. Sikulix
Selenium had several advantages for our specific testing needs for this assignment. One of which being automation of web browsers. It allows direct interaction with elements using precise selectors, making tests reliable and maintainable. Another advantage is the integration with assertions and scripts. Selenium provides built-in commands for assertions, waits, and interactions, which we made sure to use as they are essential for automated verification.

However, Selenium also has some limitations compared to SikuliX. For example, it can not test non-HTML elements easily. For image-based elements or complex canvas interactions, Selenium can not interact directly, whereas SikuliX can perform image recognition. Selenium is also dependent on selectors. If the webpage’s structure changes significantly, Selenium tests may require updates.

Overall, Selenium is great for its speed, reliability, and ability to automate end-to-end web workflows, which aligns with this assignment's needs.

# How the team work/effort was divided and managed

# Difficulties encountered, challenges overcome, and lessons learned

# Comments/feedback on the lab itself
