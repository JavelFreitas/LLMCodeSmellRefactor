# Evaluating LLMs-Driven Java Code Refactoring from a Developer’s Perspective

> Code smells are code structures that can hinder software evolution and maintenance. To counter the negative effects, developers can use refactoring techniques to improve software design, enhancing understandability and maintainability, which can reduce modification costs. Large Language Models (LLMs) are computational models trained on vast amounts of data, allowing them to perform downstream tasks such as summarization, code generation, and refactoring. Most studies have investigated LLMs in code generation and code smell refactoring, examining different prompts, training techniques, models, and suggestion strategies. However, we need to move forward in understanding developers’ perspectives regarding LLM-driven code refactoring. This paper aims to investigate how LMMs support code smell refactoring from the developers’ perceptions and its influence on code quality. To this end, we conducted an empirical study with 48 developers who applied pair refactoring in 535 instances of the following code smells: Data Class, Long Method, Feature Envy, and Long Parameter List. We assigned each team to one LLM: ChatGPT (GPT-4o), Github Copilot (Claude 3.5 Sonnet), and Gemini (1.5 Flash).

## Repository Structure

- ``src/`` Source code of the project.
- ``questionnaires/`` Questionnaires used in the study.
- ``data/`` Data used in the study.
  - ``pre-questionnaire_responses.xlsx``: Responses to the pre-questionnaire.
  - ``post-questionnaire_responses.xlsx``: Responses to the post-questionnaire.
  - ``project_metrics.xlsx``: Base project and refactored project metrics.
  - ``qualitative_analysis.xlsx``: Qualitative analysis of the questionnaire responses.
- ``rules.xml``: PMD rules used to identify code smells.


## Identifying code smells

1. In this project you can use PMD and the file "rules.xml" to identify: Data Classes (DC): Long Method (LM), Long Parameter List (LPL)

    ``
     pmd.bat check -f json -R .\regras.xml -d .\ -r ./All.json
    ``
2. You can use IntelliJDeodorant to identify: Feature Envy (FE). (You must use IntelliJ 2021.2.4 since it's the latest version that the plugin works on)

## Refactoring

The project has 100 test methods with 228 total assertions surrounding the code smells behaviour.
When refactoring the code with any LLM, you can run the tests to identify any change in the outcome, since
refactoring only alters the internal structure without altering the external behaviour.


## This project contains the following code smells:

### 5 Data Classes
- Card, Reference, ToDo, Task, SearchLog

### 5 Long Method
- LeitnerSystem, KanbanView, ToDoTracker, StudyMaterial, StudyGoal

### 5 Feature Envy
- TimelineView, GeneralSearch, StudyCardsController, MaterialSearch, RegistrySearch

### 5 Long Parameter List
- AudioReference, HabitTracker, StudyObjective, StudyPlan, StudyTaskManager



