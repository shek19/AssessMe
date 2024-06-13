document.addEventListener("DOMContentLoaded", function() {
    const addChapterBtn = document.getElementById("addChapterBtn");
    const testChaptersContainer = document.getElementById("testChaptersContainer");

    addChapterBtn.addEventListener("click", function() {
        const chapterDiv = document.createElement("div");
        chapterDiv.className = "chapter";

/*
        testChaptersContainer.innerHTML = `
        <select id="chapters" name="chapters">
            <!-- Options will be populated dynamically -->
        </select>

        <label for="easyQuestions">Easy Questions:</label>
        <input type="number" id="easyQuestions" name="easyQuestions" required><br><br>

        <label for="difficultQuestions">Difficult Questions:</label>
        <input type="number" id="difficultQuestions" name="difficultQuestions" required><br><br>

        <label for="complexQuestions">Complex Questions:</label>
        <input type="number" id="complexQuestions" name="complexQuestions" required><br><br>
        `;


        // Create a select dropdown for chapters
        const chapterDropdown = document.createElement('label');
        chapterDropdown.textContent = 'Select Chapter :';
        const chapterDropdownSelect = document.createElement("select");
        chapterDropdownSelect.name = "chapter";
        //chapterDropdownSelect.id = "chapter";
        chapterDropdownSelect.required = true;
*/



/*
        const chapterInput = document.createElement("input");
        chapterInput.type = "text";
        chapterInput.placeholder = "Chapter Name";
        chapterInput.required = true;
*/
/*
        testChaptersContainer.innerHTML = `
        <select id="chapterDropdownSelect" name="chapterDropdownSelect">
            <!-- Options will be populated dynamically -->
        </select>
        `;
*/

        const chapterDropdownSelect = document.createElement("select");
        chapterDropdownSelect.name = "chapter";
        chapterDropdownSelect.id = "chapterDropdownSelect";

        // Call a function to populate the dropdown with chapters
        populateChapterDropdown(chapterDropdownSelect);

        const easyInput = document.createElement("input");
        easyInput.type = "number";
        easyInput.placeholder = "Easy Questions";
        easyInput.required = true;

        const difficultInput = document.createElement("input");
        difficultInput.type = "number";
        difficultInput.placeholder = "Difficult Questions";
        difficultInput.required = true;

        const complexInput = document.createElement("input");
        complexInput.type = "number";
        complexInput.placeholder = "Complex Questions";
        complexInput.required = true;

        chapterDiv.appendChild(chapterDropdownSelect);
        chapterDiv.appendChild(easyInput);
        chapterDiv.appendChild(difficultInput);
        chapterDiv.appendChild(complexInput);

        testChaptersContainer.appendChild(chapterDiv);
    });

    const createTestForm = document.getElementById("createTestForm");
    createTestForm.addEventListener("submit", function(event) {
        event.preventDefault();

        const formData = {
            testName: document.getElementById("testName").value,
            subject: { id: document.getElementById("subject").value },
            conditions: document.getElementById("conditions").value,
            startTime: document.getElementById("startTime").value,
            durationInMinutes: parseInt(document.getElementById("duration").value),
            testChapters: []
        };

        const chapterDivs = document.querySelectorAll(".chapter");
        chapterDivs.forEach(chapterDiv => {
            const chapterName = chapterDiv.querySelector("select").value;
            // const chapterName = chapterDiv.querySelector("input[type='text']").value;
            const easyQuestions = parseInt(chapterDiv.querySelector("input[type='number'][placeholder='Easy Questions']").value);
            const difficultQuestions = parseInt(chapterDiv.querySelector("input[type='number'][placeholder='Difficult Questions']").value);
            const complexQuestions = parseInt(chapterDiv.querySelector("input[type='number'][placeholder='Complex Questions']").value);

            const testChapter = {
                // chapter: { name: chapterName },
                chapter: { id: chapterName },
                easyQuestions: easyQuestions,
                difficultQuestions: difficultQuestions,
                complexQuestions: complexQuestions
            };

            formData.testChapters.push(testChapter);
        });

        console.log(formData);

        // Send formData to backend API (e.g., using fetch)
        fetch("http://localhost:8080/tests/create", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(formData)
        })
        .then(response => response.json())
        .then(data => {
            console.log("Test created:", data);
            alert("Test created successfully!");
        })
        .catch(error => {
            console.error("Error creating test:", error);
            alert("Error creating test.");
        });
    });

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
                const chaptersDropdown = document.getElementById("chapterDropdownSelect");

                //const chaptersDropdown = document.getElementById("chapters");
                chaptersDropdown.innerHTML = "";
                chapters.forEach(chapter => {
                    const option = document.createElement("option");
                    option.value = chapter.id;
                    option.textContent = chapter.name;
                    chaptersDropdown.appendChild(option);
                });
            });
    });
    // Function to populate the chapter dropdown with options
    function populateChapterDropdown(chapterDropdownSelect) {
    const subjectId = document.getElementById("subject").value;

    fetch(`http://localhost:8080/subjects/${subjectId}/chapters`)
        .then(response => response.json())
        .then(chapters => {
            // Clear existing options before populating
            chapterDropdownSelect.innerHTML = "";

            chapters.forEach(chapter => {
                const option = document.createElement("option");
                option.value = chapter.id;
                option.textContent = chapter.name;
                chapterDropdownSelect.appendChild(option);
            });
        })
        .catch(error => {
            console.error("Error fetching chapters:", error);
        });
}
});