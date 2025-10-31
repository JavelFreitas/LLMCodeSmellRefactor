# Evaluating LLMs-Driven Java Code Refactoring from a Developer’s Perspective

> Code smells hinder software evolution. Refactoring improves design, but performing it correctly requires expertise. Recent efforts investigate Large Language Models (LLMs) for code maintenance tasks such as code generation and refactoring. However, developer perception of LLM-assisted refactoring remains underexplored. This paper empirically evaluates how developers use LLMs to refactor four code smells (Data Class, Long Method, Feature Envy, Long Parameter List) and the resulting effects on code quality. We conducted a study with 48 developers who applied refactorings with ChatGPT (GPT-4o), GitHub Copilot (Claude 3.5 Sonnet), and Gemini (1.5 Flash), producing 535 refactorings. We analyzed refactoring techniques, internal quality attributes, side-effect smell insertions, and developer feedback. Results show: (i) Extract Method, Move Method, and Introduce Parameter Object were most used; (ii) Feature Envy refactorings improved all attributes; (iii) Long Parameter List produced the highest positive improvement (Inheritance: +4.93%) and largest degradation (Cohesion: -6.07%); (iv) each LLM outperformed the others on different attributes; (v) Long Parameter List refactorings introduced 65 new smell instances; and (vi) developers reported productivity gains but noted tool dependency, supervision needs, and hallucinations.

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



