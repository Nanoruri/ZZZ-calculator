import React from "react";
import { useRegisterMaterial } from "../../hooks/useRegisterMaterial";
import "../../styles/material/resourceInputFields.css"; // 스타일 파일 추가

const ResourceInputFields: React.FC = () => {
    const {
        loading,
        error,
        resourceType,
        newResource,
        handleChange,
        addResourceAndRegister,
        setResourceType
    } = useRegisterMaterial();

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
    };

    return (
        <form onSubmit={handleSubmit} className="resource-form">
            <div className="form-group">
                <label>자원 종류:</label>
                <select value={resourceType} onChange={(e) => setResourceType(e.target.value)} className="form-control">
                    <option value="CoreSkillResource">코어 스킬 자원</option>
                    <option value="LevelUpResource">레벨업 자원</option>
                    <option value="SkillResource">스킬 자원</option>
                    <option value="BreakthroughResource">돌파 자원</option>
                </select>
            </div>

            <div className="form-group">
                <label>자원 이름:</label>
                <input type="text" name="name" value={newResource.name} onChange={handleChange} className="form-control" />
            </div>
            <div className="form-group">
                <label>자원 타입:</label>
                <input type="text" name="type" value={newResource.type} onChange={handleChange} className="form-control" />
            </div>
            {resourceType === "LevelUpResource" && (
                <div className="form-group">
                    <label>경험치:</label>
                    <input type="number" name="experience" value={newResource.experience} onChange={handleChange} className="form-control" />
                </div>
            )}
            {resourceType === "BreakthroughResource" && (
                <>
                    <div className="form-group">
                        <label>속성:</label>
                        <input type="text" name="attribute" value={newResource.attribute} onChange={handleChange} className="form-control" />
                    </div>
                    <div className="form-group">
                        <label>레벨 범위:</label>
                        <input type="number" name="levelRangeStart" value={newResource.levelRangeStart} onChange={handleChange} className="form-control" />
                        <input type="number" name="levelRangeEnd" value={newResource.levelRangeEnd} onChange={handleChange} className="form-control" />
                    </div>
                </>
            )}

            <button type="button" onClick={addResourceAndRegister} disabled={loading} className="btn btn-primary">
                재료 등록
            </button>

            {error && <p className="error-message">{error}</p>}
        </form>
    );
};

export default ResourceInputFields;