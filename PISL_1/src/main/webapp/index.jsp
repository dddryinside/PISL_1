<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Word Processor</title>
  <style>
    table, th, td {
      border: 1px solid black;
      border-collapse: collapse;
      padding: 8px;
    }
    #error {
      color: red;
    }
  </style>
</head>
<body>
<h1>Enter Words:</h1>
<form id="wordForm">
  <input type="text" id="wordsInput" name="word" required>
  <button type="submit">Submit</button>
</form>

<div id="error"></div>

<h2>Word Table</h2>
<table>
  <thead>
  <tr>
    <th>Starts with "a"</th>
    <th>Starts with "b"</th>
    <th>Starts with "c"</th>
    <th>Others</th>
  </tr>
  </thead>
  <tbody id="wordTableBody">
  <tr>
    <td id="columnA"></td>
    <td id="columnB"></td>
    <td id="columnC"></td>
    <td id="columnOther"></td>
  </tr>
  </tbody>
</table>

<script>
  document.getElementById('wordForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const errorDiv = document.getElementById('error');
    errorDiv.textContent = '';

    const words = document.getElementById('wordsInput').value;

    fetch('post-words', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      body: new URLSearchParams({ word: words })
    })
            .then(response => response.json().then(data => ({ status: response.status, body: data })))
            .then(({ status, body }) => {
              if (status === 200) {
                document.getElementById('columnA').textContent = body.a.join(', ');
                document.getElementById('columnB').textContent = body.b.join(', ');
                document.getElementById('columnC').textContent = body.c.join(', ');
                document.getElementById('columnOther').textContent = body.other.join(', ');
              } else {
                errorDiv.textContent = body.error || 'Unexpected error occurred.';
              }
            })
            .catch(error => {
              errorDiv.textContent = 'Network error occurred.';
              console.error('Error:', error);
            });
  });
</script>
</body>
</html>