document.addEventListener("DOMContentLoaded", function () {
  document
    .getElementById("quizForm")
    .addEventListener("submit", function (event) {
      event.preventDefault();
      const quizId = document.getElementById("quizId").value;

      fetch(`http://localhost:8080/quiz/get/${quizId}`)
        .then((response) => response.json())
        .then((data) => {
          const quizQuestions = document.getElementById("quizQuestions");
          quizQuestions.innerHTML = "<h3>Quiz Questions</h3>";
          data.forEach((question) => {
            quizQuestions.innerHTML += `
              <div class="card mb-3">
                <div class="card-body">
                  <h5 class="card-title">${question.question_title}</h5>
                  <ul class="list-group">
                    <li class="list-group-item">Option 1: ${question.option1}</li>
                    <li class="list-group-item">Option 2: ${question.option2}</li>
                    <li class="list-group-item">Option 3: ${question.option3}</li>
                    <li class="list-group-item">Option 4: ${question.option4}</li>
                  </ul>
                </div>
              </div>`;
          });
        })
        .catch((error) => console.error("Error fetching quiz:", error));
    });
});
