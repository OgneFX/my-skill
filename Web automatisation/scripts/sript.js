document.querySelectorAll('.service-button').forEach((button, index) => {
  button.addEventListener('click', () => {
    // Сброс всех выделений
    document.querySelectorAll('.service-button').forEach(btn => btn.classList.remove('expanded', 'bottom-right'));

    // Добавляем класс 'expanded'
    button.classList.add('expanded');

    // Если это девятая (или последняя) кнопка, добавляем специальный класс
    if (index === 8) { // Здесь используется индекс 8 для 9-й кнопки
      button.classList.add('bottom-right');
    }
  });
});