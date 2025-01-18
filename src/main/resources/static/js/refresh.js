document.getElementById('refreshButton').addEventListener('click', function () {
    fetch('/api/graph/refresh', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('authToken')
        }
    })
        .then(response => response.json())
        .then(data => {
            console.log('Response from server:', data);
            if (data.status === 'OK') {
                console.log('Files refreshed successfully');
            } else {
                console.error('Failed to refresh files:', data.message);
            }
        })
        .catch(error => {
            console.error('Error during refresh:', error);
        });
});