import React, { useState } from 'react';
import Delaunator from 'delaunator';
import './App.css';
import TriangleCanvas from './TriangleCanvas';
import Axios from 'axios';

function App() {

  let [state, setState] = useState({points: undefined, triangles: undefined});
  const triangulate = () => {
    Axios.post('http://localhost:8080/rentals/region', {
      "locations": [
        {
          "name": "123AAA",
          "xCoord": 1.0,
          "yCoord": 2.0
        },
        {	
          "name": "456AAA",
          "xCoord": 3.0,
          "yCoord": 1.0
        },
        {
          "name": "789AAA",
          "xCoord": 5.0,
          "yCoord": 7.0
        },
        {
          "name": "123BBB",
          "xCoord": 8.33,
          "yCoord": 9.22
        },
        {
          "name": "456BBB",
          "xCoord": 1.33,
          "yCoord": 3.22
        },
        {
          "name": "789BBB",
          "xCoord": 4.33,
          "yCoord": 5.22
        }
      ]
    }).then(() => {
      Axios.get('http://localhost:8080/rentals/region')
        .then((res) => {
          const points = res.data.map((point) => {
            return [parseFloat(point.x), parseFloat(point.y)]
          });
          let triangulator = Delaunator.from(points);
          setState({points: points, triangles: triangulator.triangles})
        })
    })
  }

  return (
    <div>
      <h1 onClick={triangulate}>Send Data</h1>

      <TriangleCanvas triangles={state.triangles} points={state.points}></TriangleCanvas>
    </div>
  )
}

export default App;
