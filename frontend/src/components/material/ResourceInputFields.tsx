// ResourceInputFiled.tsx
import React from "react";
import { useRegisterMaterial } from "../../hooks/useRegisterMaterial";

const ResourceInputFiled: React.FC = () => {
    const {
        loading,
        error,
        resourceType,
        newResource,
        handleChange,
        addResourceAndRegister, // 변경된 함수
        setResourceType
    } = useRegisterMaterial();

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>자원 종류:</label>
                <select value={resourceType} onChange={(e) => setResourceType(e.target.value)}>
                    <option value="CoreSkillResource">코어 스킬 자원</option>
                    <option value="LevelUpResource">레벨업 자원</option>
                    <option value="SkillResource">스킬 자원</option>
                    <option value="BreakthroughResource">돌파 자원</option>
                </select>
            </div>

            {/* 자원 입력 필드 */}
            <div>
                <label>자원 이름:</label>
                <input type="text" name="name" value={newResource.name} onChange={handleChange} />
            </div>
            <div>
                <label>자원 타입:</label>
                <input type="text" name="type" value={newResource.type} onChange={handleChange} />
            </div>
            {resourceType === "LevelUpResource" && (
                <div>
                    <label>경험치:</label>
                    <input type="number" name="experience" value={newResource.experience} onChange={handleChange} />
                </div>
            )}
            {resourceType === "BreakthroughResource" && (
                <>
                    <div>
                        <label>속성:</label>
                        <input type="text" name="attribute" value={newResource.attribute} onChange={handleChange} />
                    </div>
                    <div>
                        <label>레벨 범위:</label>
                        <input type="number" name="levelRangeStart" value={newResource.levelRangeStart} onChange={handleChange} />
                        <input type="number" name="levelRangeEnd" value={newResource.levelRangeEnd} onChange={handleChange} />
                    </div>
                </>
            )}

            {/* 자원 추가 및 등록 버튼 */}
            <button type="button" onClick={addResourceAndRegister} disabled={loading}>
                재료 등록
            </button>

            {error && <p>{error}</p>}
        </form>
    );
};

export default ResourceInputFiled;
