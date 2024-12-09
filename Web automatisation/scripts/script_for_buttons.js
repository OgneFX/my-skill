
document.querySelectorAll('.contact-form').forEach(form => {
  form.querySelector('.login-box-button').addEventListener('click', async () => {
    const name = form.querySelector('.user-name').value.trim();
    const phone = form.querySelector('.user-phone').value.trim();

    
    if (!name || !phone) {
      alert('Пожалуйста, заполните все поля!');
      return;
    }

    const data = { name, phone };

    try {
      const response = await fetch('http://127.0.0.1:5000/send_message', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data),
      });

      const result = await response.json();
      
      if (response.ok) {
        // alert(result.message);
        form.reset(); 
      } else {
        // alert('Ошибка: ' + result.message);
      }
    } catch (error) {
      console.log('Ошибка:', error);
      // alert('Произошла ошибка при отправке сообщения. Попробуйте ещё раз.');
      console.log("Sending data:", data);
    }
  });
});