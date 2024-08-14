document.addEventListener("DOMContentLoaded", function () {
  const questionsTableBody = document.getElementById("questionsTableBody");
  const fetchByCategoryButton = document.getElementById("fetchByCategory");
  const categoryInput = document.getElementById("category");
  const addQuestionForm = document.getElementById("addQuestionForm");
  const updateQuestionForm = document.getElementById("updateQuestionForm");

  const apiURLFetchQuestions = "http://localhost:8080/allquestions";
  const apiURLDeleteQuestion = "http://localhost:8080/delete";
  const apiURLAddQuestion = "http://localhost:8080/add";
  const apiURLUpdateQuestion = "http://localhost:8080/update";
  const apiURLFetchQuestionById = "http://localhost:8080/question";

  function fetchQuestions() {
    fetch(apiURLFetchQuestions)
      .then((response) => response.json())
      .then((data) => {
        questionsTableBody.innerHTML = ""; // Clear table before adding new rows
        data.forEach((question) => {
          const row = document.createElement("tr");
          row.innerHTML = `
            <td>${question.id}</td>
            <td>${question.question_title}</td>
            <td>${question.option1}</td>
            <td>${question.option2}</td>
            <td>${question.option3}</td>
            <td>${question.option4}</td>
            <td>${question.right_answer}</td>
            <td>${question.difficulty_level}</td>
            <td>${question.category}</td>
            <td><button class="delete-btn" data-id="${question.id}">Delete</button></td>
          `;
          questionsTableBody.appendChild(row);
        });
        attachDeleteHandlers();
      })
      .catch((error) => {
        console.log(error);
      });
  }

  function attachDeleteHandlers() {
    document.querySelectorAll(".delete-btn").forEach((button) => {
      button.addEventListener("click", function () {
        const id = this.getAttribute("data-id");
        if (confirm("Are you sure you want to delete this question?")) {
          deleteQuestion(id);
        }
      });
    });
  }

  function deleteQuestion(id) {
    fetch(`${apiURLDeleteQuestion}/${id}`, {
      method: "DELETE",
    })
      .then((response) => response.text())
      .then((message) => {
        alert(message);
        fetchQuestions(); // Refresh the table after deletion
      })
      .catch((error) => {
        console.error("Error deleting question:", error);
      });
  }

  fetchByCategoryButton.addEventListener("click", function () {
    const category = categoryInput.value;
    const apiURLFetchQuestionsByCategory = `http://localhost:8080/category/${category}`;
    fetch(apiURLFetchQuestionsByCategory)
      .then((response) => response.json())
      .then((data) => {
        // Clear previous table rows
        questionsTableBody.innerHTML = "";

        if (data.length > 0) {
          data.forEach((question) => {
            const row = document.createElement("tr");
            row.innerHTML = `
              <td>${question.id}</td>
              <td>${question.question_title}</td>
              <td>${question.option1}</td>
              <td>${question.option2}</td>
              <td>${question.option3}</td>
              <td>${question.option4}</td>
              <td>${question.right_answer}</td>
              <td>${question.difficulty_level}</td>
              <td>${question.category}</td>
              <td><button class="delete-btn" data-id="${question.id}">Delete</button></td>
            `;
            questionsTableBody.appendChild(row);
          });
        } else {
          // Handle case where no questions are found
          const row = document.createElement("tr");
          row.innerHTML = `<td colspan="10">No questions found for category "${category}"</td>`;
          questionsTableBody.appendChild(row);
        }
        attachDeleteHandlers();
      })
      .catch((error) => {
        console.log("Error fetching questions by category:", error);
      });
  });

  addQuestionForm.addEventListener("submit", function (event) {
    event.preventDefault(); // Prevent default form submission

    const formData = new FormData(addQuestionForm);
    const question = {
      question_title: formData.get("questionTitle"),
      option1: formData.get("option1"),
      option2: formData.get("option2"),
      option3: formData.get("option3"),
      option4: formData.get("option4"),
      right_answer: formData.get("rightAnswer"),
      difficulty_level: formData.get("difficultyLevel"),
      category: formData.get("categoryInput"),
    };

    fetch(apiURLAddQuestion, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(question),
    })
      .then((response) => response.json())
      .then((data) => {
        alert("Question added successfully!");
        addQuestionForm.reset(); // Reset form fields
        fetchQuestions(); // Refresh the table with updated data
      })
      .catch((error) => {
        console.error("Error adding question:", error);
      });
  });

  // Update question form submission handler
  updateQuestionForm.addEventListener("submit", function (event) {
    event.preventDefault();

    // Check if the form elements exist before accessing their values
    const updateId = document.getElementById("updateId");
    const updateQuestionTitle = document.getElementById("updateQuestionTitle");
    const updateOption1 = document.getElementById("updateOption1");
    const updateOption2 = document.getElementById("updateOption2");
    const updateOption3 = document.getElementById("updateOption3");
    const updateOption4 = document.getElementById("updateOption4");
    const updateRightAnswer = document.getElementById("updateRightAnswer");
    const updateDifficultyLevel = document.getElementById(
      "updateDifficultyLevel"
    );
    const updateCategory = document.getElementById("updateCategory");

    if (
      updateId &&
      updateQuestionTitle &&
      updateOption1 &&
      updateOption2 &&
      updateOption3 &&
      updateOption4 &&
      updateRightAnswer &&
      updateDifficultyLevel &&
      updateCategory
    ) {
      const id = updateId.value;
      const updatedQuestion = {
        question_title: updateQuestionTitle.value,
        option1: updateOption1.value,
        option2: updateOption2.value,
        option3: updateOption3.value,
        option4: updateOption4.value,
        right_answer: updateRightAnswer.value,
        difficulty_level: updateDifficultyLevel.value,
        category: updateCategory.value,
      };

      fetch(`${apiURLUpdateQuestion}/${id}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(updatedQuestion),
      })
        .then((response) => response.json())
        .then((data) => {
          alert("Question updated successfully!");
          fetchQuestions(); // Refresh the table after update
        })
        .catch((error) => {
          console.error("Error updating question:", error);
        });
    } else {
      console.error("One or more form elements are missing.");
    }
  });

  // Fetch questions initially
  fetchQuestions();
});
