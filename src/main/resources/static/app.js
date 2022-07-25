$('#loadCourses').click(() => {
    reloadCourses();
})

function reloadCourses() {
    $('#college-container').empty();

    fetch("http://localhost:8080/courses")
        .then(response => response.json())
        .then(json => json.forEach(course => {
            let tableRow = '<tr>' +

                '<td>' + course.title + '</td>' +
                '<td>' + course.college.name + '</td>' +
                '<td>' +
                '<button class="edit-btn" data-course-id = "' + course.id + '">Edit</button>' +
                '<button class="delete-btn" data-course-id = "' + course.id + '">Delete</button>' +
                '</td>' +

                '</tr>'
            $("#college-container").append(tableRow);
        }));
}

$('body').on('click', 'button.delete-btn', function () {
    let courseId = $(this).data('course-id');
    console.log("Course id to delete is: " + courseId);

    fetch("http://localhost:8080/courses/" + courseId, {
        method: 'DELETE'

    }).then(_ => reloadCourses())
});
