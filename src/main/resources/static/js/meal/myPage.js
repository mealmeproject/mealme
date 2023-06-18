const modal = document.querySelector('.modal');
const mealImgBoxes = document.querySelectorAll(".meal-img-box");
const closeModalButton = document.getElementById("close-modal");

mealImgBoxes.forEach(function (box) {
    box.addEventListener("click", openModal);
});

function openModal(event) {
    event.stopPropagation();
    modal.setAttribute("style", "display: block;");
}

closeModalButton.addEventListener('click', function() {
    event.stopPropagation();
    console.log("닫는다!");
    modal.setAttribute("style", "display: none;");
});



