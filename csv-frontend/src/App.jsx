import { useState } from "react";
import axios from "axios";

function App() {
  const [file, setFile] = useState(null);
  const [response, setResponse] = useState(null);

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
  };

  const uploadFile = async () => {
    if (!file) {
      alert("Please select a file");
      return;
    }

    const formData = new FormData();
    formData.append("file", file);

    try {
      const res = await axios.post(
        "http://localhost:8080/api/upload",
        formData
      );
      setResponse(res.data);
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <div style={{ padding: "30px", fontFamily: "Arial" }}>
      <h2>CSV Upload & Validation</h2>

      <input type="file" onChange={handleFileChange} />
      <br /><br />

      <button onClick={uploadFile}>Upload</button>

      <br /><br />

      {response && (
        <div>
          <h3>Status: {response.status}</h3>

          {response.errors.length > 0 ? (
            <table border="1" cellPadding="10">
              <thead>
                <tr>
                  <th>Row</th>
                  <th>Error Message</th>
                </tr>
              </thead>
              <tbody>
                {response.errors.map((err, index) => (
                  <tr key={index}>
                    <td>{err.row}</td>
                    <td>{err.message}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          ) : (
            <p>No Errors </p>
          )}
        </div>
      )}
    </div>
  );
}

export default App;