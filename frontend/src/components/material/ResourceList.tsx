import React from "react";
import "../../styles/resourceList.css";

interface ResourceListProps {
    title: string;
    resources: Record<string, number>;
}

const ResourceList: React.FC<ResourceListProps> = ({ title, resources }) => (
    <div className="resource-list">
        <h3>{title}</h3>
        <ul>
            {Object.entries(resources).map(([name, count]) => (
                <li key={name}>{name}: {count}개</li>
            ))}
        </ul>
    </div>
);

export default ResourceList;