document.querySelectorAll('.service-button').forEach(button => {
    button.addEventListener('click', () => {
      document.querySelectorAll('.service-button').forEach(btn => btn.classList.remove('expanded'));
      button.classList.add('expanded');
    });
  });