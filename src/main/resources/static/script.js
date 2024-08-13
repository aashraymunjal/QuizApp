document.addEventListener("DOMContentLoaded", function () {
  const questionsTableBody = document.getElementById("questionsTableBody");
  const fetchByCategoryButton = document.getElementById("fetchByCategory");
  const categoryInput = document.getElementById("category");

  const apiURLFetchQuestions = "http://localhost:8080/allquestions";
  const apiURLDeleteQuestion = "http://localhost:8080/delete";

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

        if (data) {
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
          row.innerHTML = `<td colspan="9">No questions found for category "${category}"</td>`;
          questionsTableBody.appendChild(row);
        }
        attachDeleteHandlers();
      })
      .catch((error) => {
        console.log("Error fetching questions by category:", error);
      });
  });

  fetchQuestions(); // Initial fetch
});
