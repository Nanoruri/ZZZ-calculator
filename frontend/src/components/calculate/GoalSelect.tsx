import React from "react";
import "../../styles/goalSelect.css";

interface GoalSelectProps {
    label: string;
    id: string;
    value: number;
    onChange: (e: React.ChangeEvent<HTMLSelectElement>) => void;
    options: number[];
}

const GoalSelect: React.FC<GoalSelectProps> = ({ label, id, value, onChange, options }) => (
    <div className="goal-section">
        <label htmlFor={id}>{label}</label>
        <select id={id} value={value} onChange={onChange}>
            {options.map(option => (
                <option key={option} value={option}>{option}</option>
            ))}
        </select>
    </div>
);

export default GoalSelect;