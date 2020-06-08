import React from 'react';
import { render } from '@testing-library/react';


const ZOOM_FACTOR = 50;
export default class TriangleCanvas extends React.Component {
    constructor(props) {
        super(props);
        this.canvasRef = React.createRef();
    }
    componentDidUpdate() {
        this.drawTriangles();
    }

    drawTriangles() {
        const triangles = this.props.triangles;
        const points = this.props.points;
        const ctx = this.canvasRef.current.getContext('2d');
        console.log(triangles, points)
        ctx.beginPath();
        triangles.forEach((t, i) => {
            if (i === 0) {
                ctx.moveTo(points[t][0] * ZOOM_FACTOR, points[t][1] * ZOOM_FACTOR);
            } else {
                ctx.lineTo(points[t][0] * ZOOM_FACTOR, points[t][1] * ZOOM_FACTOR);
            }
        })
        ctx.closePath();
        ctx.stroke();
    }


    render() {
        return (
            <div>
                <canvas ref={this.canvasRef} width="1000" height="1000"></canvas>
            </div>
        )
    }
}
