function composeRequest(body) {
  return {
    method: 'DELETE',
    body: body,
    headers: new Headers({
      'Content-Type': 'application/json'
    }),
    mode: 'cors',
    cache: 'default'
  };
}

function onResponse(response) {
  console.log(response);
  if (response.ok) {
    location.reload();
  }
  return;
}

function onError(error) {
  var notificationClassName = 'is-hidden';
  console.error('Error:', error);
  document.querySelector('#notification').classList.remove(notificationClassName);
  return;
}

function makeDeleteRequestTo(url, idName) {

  var request = composeRequest(JSON.stringify({
    'column_name': idName
  }));

  fetch(url, request)
    .then(onResponse)
    .catch(onError);
}