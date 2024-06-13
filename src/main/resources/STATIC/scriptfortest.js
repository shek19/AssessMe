// script.js

document.addEventListener("DOMContentLoaded", function() {
    // Fetch subjects from backend and populate dropdown
    fetch("http://localhost:8080/subjects")
        .then(response => response.json())
        .then(subjects => {
            const subjectDropdown = document.getElementById("subject");
            subjects.forEach(subject => {
                const option = document.createElement("option");
                option.value = subject.id;
                option.textContent = subject.name;
                subjectDropdown.appendChild(option);
            });
        });

    // Update chapters dropdown when subject selection changes
    document.getElementById("subject").addEventListener("change", function() {
        const subjectId = this.value;
        fetch(`http://localhost:8080/subjects/${subjectId}/chapters`)
            .then(response => response.json())
            .then(chapters => {
                const chaptersDropdown = document.getElementById("chapters");
                chaptersDropdown.innerHTML = "";
                chapters.forEach(chapter => {
                    const option = document.createElement("option");
                    option.value = chapter.id;
                    option.textContent = chapter.name;
                    chaptersDropdown.appendChild(option);
                });
            });
    });

    // Handle form submission
    document.getElementById("createTestForm").addEventListener("submit", function(event) {
        event.preventDefault();

        const formData = new FormData(this);
        const data = {};
        formData.forEach((value, key) => {
            data[key] = value;
        });

        fetch("http://localhost:8080/tests/create", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        })
        .then(response => {
            if (response.ok) {
                alert("Test created successfully!");
                // Redirect or perform any other action
            } else {
                alert("Error creating test.");
            }
        })
        .catch(error => {
            console.error("Error:", error);
            alert("Error creating test.");
        });
    });
});
