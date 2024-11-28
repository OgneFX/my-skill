const buttons = document.querySelectorAll('.grid-item');

buttons.forEach((button, index) => {
  button.addEventListener('click', (event) => {
    event.stopPropagation(); 
    buttons.forEach(btn => btn.classList.remove('expanded', 'bottom-right'));
    button.classList.add('expanded');
    if (index === 8) {
      button.classList.add('bottom-right');
    }
  });
});


document.addEventListener('click', (event) => {
  const isButton = event.target.closest('.grid-item');
  if (!isButton) { 
    buttons.forEach(btn => btn.classList.remove('expanded', 'bottom-right'));
  }
});

// Header анимация

document.addEventListener("DOMContentLoaded", () => {
  const buttons = document.querySelectorAll(".view-button");
  const image = document.querySelector(".main-image");
  const textElement = document.querySelector(".b-text");
  const images = [
      "img/camera-tiandy2.png",
      "img/oven-plk.png",
      "img/wirenboard-plk7.png",
      "img/camera-tiandy.png",
      "img/camera-tiandy5.png",
  ];

  const texts = [
    "Проектирование и установка систем автоматизации и безопасности.",
    "Текст для изображения 2",
    "Текст для изображения 3",
    "Текст для изображения 4",
    "Текст для изображения 5",
  ];



  let currentIndex = 0;
  let delay = 5000;
  let interval;

  const updateActiveButton = (index) => {
      buttons.forEach((btn, i) => {
          btn.classList.toggle("active", i === index);
      });
  };

  const updateImage = (index) => {
      image.src = images[index];
      textElement.textContent = texts[index];
  };

  const startCycle = () => {
      interval = setInterval(() => {
          currentIndex = (currentIndex + 1) % buttons.length;
          updateActiveButton(currentIndex);
          updateImage(currentIndex);
      }, delay);
  };

 
  buttons.forEach((button, index) => {
      button.addEventListener("click", () => {
          clearInterval(interval);
          currentIndex = index;
          updateActiveButton(currentIndex);
          updateImage(currentIndex);
          delay = 10000; 
          startCycle();
      });
  });

  
  updateActiveButton(currentIndex);
  updateImage(currentIndex);
  startCycle();
});
