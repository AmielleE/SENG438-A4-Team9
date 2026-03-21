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
To assess test quality and user based behaviour, this lab investigates mutation testing and GUI automation. We evaluated the effectiveness of our tests in detecting defects by injecting faults into Range and DataUtilities using PIT. Next, we enhanced the test suites by focusing on the vulnerabilities exposed by the surviving mutants. Simultaneously, realistic user workflows on web applications were automated using Selenium. 

Together, these tasks highlight the difference between coverage and actual fault detection.
# Analysis of 10 Mutants of the Range class 

Mutation testing was performed on the Range class using PIT. A total of 1259 mutants were generated, of which 860 were killed, resulting in a mutation score of 68%.

Below is an analysis of 10 representative mutants:

1. Relational Operator Replacement ( < -> < = )
This mutation changes boundary conditions. It was killed by tests that verify behavior at exact boundary values, ensuring correctness for inclusive/exclusive comparisons.

2. Relational Operator Replacement ( > -> >= )
This mutant was also killed by boundary tests. The test suite includes edge cases where values are exactly equal to the upper or lower bounds.

3. Negated Conditional ( == -> != )
This mutation alters logical conditions. It was successfully killed because test cases verify expected equality behavior explicitly.

4. Arithmetic Operator Replacement ( + -> - )
This mutant affects calculations within range expansion or shifting. It was killed due to tests validating exact numerical outputs.

5. Increment Mutation ( a++ -> a-- )
This mutation modifies iteration logic. It was killed because the test suite checks cumulative results that depend on correct iteration.

6. Return Value Replacement ( returning incorrect constant )
Some mutants replaced return values with incorrect constants (e.g., 0 or null). These were killed because assertions check exact expected outputs.

7. Boundary Condition Mutation ( removing equality check )
These mutants survived in some cases, indicating that certain boundary conditions were not strictly tested.

8. Loop Boundary Mutation ( altering loop limits )
Some mutants modifying loop bounds survived, suggesting missing edge-case tests for iteration limits.

9. Equivalent Mutants ( no functional change )
Certain mutations did not change program behavior (e.g., redundant arithmetic changes), so they survived but are considered equivalent mutants.

10. Conditional Negation in Edge Cases
Some survived mutants indicate that specific edge scenarios were not covered by the test suite, particularly rare boundary combinations.

# Report all the statistics and the mutation score for each test class
* Range Class
Line Coverage: 98% (101/103)
Mutation Coverage: 68% (860/1259)
Test Strength: 70% (860/1226)

* DataUtilities Class
Line Coverage: 51% (41/80)
Mutation Coverage: 46% (319/687)
Test Strength: 94% (319/341)



# Analysis drawn on the effectiveness of each of the test classes
The Range test suite demonstrates very high line coverage of 98%, indicating that most lines of code are executed during testing. However, the mutation coverage is lower, being 68% which suggests that although the code is executed, not all logical faults are detected.

This highlights that high line coverage does not necessarily imply strong test quality.

On the other hand, the DataUtilities test suite has lower line coverage of 51%, but a high test strength 94%, meaning that the tests that do exist are effective at detecting faults.

Thus, 
Range: Broad coverage but missing some edge case assertions
DataUtilities: Fewer tests, but strong fault detection

# A discussion on the effect of equivalent mutants on mutation score accuracy
Equivalent mutants are mutations that do not change the observable behavior of the program. These mutants cannot be killed by any test case, regardless of its quality.

In this Lab, some surviving mutants are likely equivalent mutants. Their presence lowers the mutation score artificially, making the test suite appear weaker than it actually is. This introduces a limitation in mutation testing: 

* It is difficult to automatically detect equivalent mutants
* Manual inspection is often required
* score may underestimate true test effectiveness


# A discussion of what could have been done to improve the mutation score of the test suites
To improve mutation scores, several strategies could be used. First, additional boundary tests should be introduced, particularly focusing on edge values such as inputs that are exactly equal to the minimum or maximum limits. These cases are often where faults occur and are commonly targeted by mutation operators.

Secondly, assertions can be strengthened by using stricter checks, such as assertSame or verifying exact expected values rather than relying on weaker comparisons. This ensures that even subtle deviations in behavior are detected by the test suite.

It is also important to include a wider range of edge cases in testing. This includes scenarios such as empty inputs, single element inputs, and negative values, all of which help ensure the robustness of the system under unusual or extreme conditions.

Another effective approach is to specifically target surviving mutants. By analyzing the mutants that were not killed, new test cases can be designed to address the gaps in the existing test suite and improve its fault-detection capability.

Finally, increasing branch coverage is essential. This involves ensuring that all logical conditions in the code are tested for both true and false outcomes, which helps expose hidden defects and improves the overall effectiveness of the tests.


# Why do we need mutation testing? Advantages and disadvantages of mutation testing
Mutation testing offers many important advantages when evaluating a test suite. One  benefit is that it measures the quality of the tests, rather than just how much code is executed. Traditional metrics like line coverage only indicate whether code has been run, however mutation testing goes further by checking whether the tests can actually detect faults introduced into the program. In addition, mutation testing helps identify weak or missing test cases. When mutants survive, it signals that certain scenarios or edge cases are not being properly tested, allowing developers to improve their test design. Thus, it contributes to building more robust and reliable test suites, since tests are refined to catch a wider range of potential defects.

However, mutation testing also has several disadvantages. One limitation is that it is computationally expensive, as the system must generate and run a large number of modified versions of the program (mutants) each requiring execution of the full test suite. This can significantly increase testing time and resource usage. Another challenge is the difficulty in detecting equivalent mutants mutations that do not actually change the program’s behavior. These mutants cannot be killed by any test, yet they still affect the mutation score making it harder to accurately assess test effectiveness. Lastly, mutation testing can produce a very large number of mutants, which can be overwhelming to analyze manually. This makes it time consuming to review results and determine which surviving mutants indicate real weaknesses in the test suite.


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

Pair 1: Fatma & Amielle focused on selenium test design and automation & verified the mutation testing (analysis, improving test suites) done by pair 2.

Pair 2: Josral, Faris & Erioluwa Josral focused on mutation testing (analysis, improving test suites) & verified the selenium test design and automation performed by pair 1.

The lab report was a group effort curated with the efforts of everyone; it was also reviewed before final submission by the whole group.
# Difficulties encountered, challenges overcome, and lessons learned
It was difficult to interpret the mutation results, particularly when it came to separating weak test coverage from equivalent mutants. Instead of just adding more tests, targeted test design was needed to improve mutation scores. We used better locators and waits to fix the timing problems and unstable selectors that caused Selenium scripts to initially fail. 

Additionally, we discovered that not every UI behaviour can be consistently automated. In general, the lab prioritised testing accuracy over cursory coverage.

# Comments/feedback on the lab itself
The lab successfully highlighted line coverage's shortcomings as a quality metric and encouraged us to create more robust test designs. Although it required a lot of setup and took some time, mutation testing was insightful. We believe some advice on managing dynamic elements would be helpful, Selenium offered hands-on experience. More organised examples would improve the SikuliX comparison testing, which felt less hands-on. All things considered, the lab was difficult but extremely applicable to actual testing procedures.
