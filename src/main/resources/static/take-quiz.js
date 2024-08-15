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
                      <li class="list-group-item">
                        <input type="radio" name="question${question.id}" value="option1" />
                        ${question.option1}
                      </li>
                      <li class="list-group-item">
                        <input type="radio" name="question${question.id}" value="option2" />
                        ${question.option2}
                      </li>
                      <li class="list-group-item">
                        <input type="radio" name="question${question.id}" value="option3" />
                        ${question.option3}
                      </li>
                      <li class="list-group-item">
                        <input type="radio" name="question${question.id}" value="option4" />
                        ${question.option4}
                      </li>
                    </ul>
                  </div>
                </div>`;
          });

          // Add submit button if not already present
          if (!document.getElementById("submitQuiz")) {
            quizQuestions.innerHTML += `
                <button id="submitQuiz" type="button" class="btn btn-success mt-3">Submit Quiz</button>`;
          }

          // Attach event listener for submit button
          document
            .getElementById("submitQuiz")
            .addEventListener("click", function () {
              const answers = data
                .map((question) => {
                  const selectedOption = document.querySelector(
                    `input[name="question${question.id}"]:checked`
                  );
                  return {
                    id: question.id,
                    response: selectedOption
                      ? selectedOption.nextSibling.textContent.trim()
                      : null,
                  };
                })
                .filter((answer) => answer.response);
              console.log("Submitting answers:", answers);

              // Send answers to the backend
              fetch(`http://localhost:8080/quiz/submit/${quizId}`, {
                method: "POST",
                headers: {
                  "Content-Type": "application/json",
                },
                body: JSON.stringify(answers),
              })
                .then((response) => response.json())
                .then((result) => {
                  alert(`Your score is: ${result}`);
                })
                .catch((error) =>
                  console.error("Error submitting quiz:", error)
                );
            });
        })
        .catch((error) => console.error("Error fetching quiz:", error));
    });
});
