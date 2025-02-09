/* useCharacterForm.ts */
import React, { useState } from "react";
import { Agent, registerCharacter } from "../ts/api/registAgent";

export const useCharacterForm = () => {
    const [formData, setFormData] = useState<Agent>({
        name: "",
        role: "",
        elementType: "",
        level: 1,
        exp: 0,
        coreSkill: { name: "", level: 1, description: "" },
        skills: Array(5).fill({ id: Date.now(), name: "", level: 1, maxLevel: 12 }),
    });
    const [message, setMessage] = useState<string | null>(null);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        const { name, value } = e.target;
        setFormData((prev) => ({
            ...prev,
            [name]: value,
        }));
    };

    const handleCoreSkillChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormData((prev) => ({
            ...prev,
            coreSkill: {
                ...prev.coreSkill,
                [name]: value,
            },
        }));
    };

    const handleSkillChange = (index: number, field: string, value: string | number) => {
        setFormData((prev) => ({
            ...prev,
            skills: prev.skills.map((skill, i) =>
                i === index ? { ...skill, [field]: value } : skill
            ),
        }));
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            await registerCharacter(formData);
            setMessage("✅ 캐릭터 등록 성공!");
        } catch (error) {
            setMessage("❌ 캐릭터 등록 실패");
        }
    };

    return { formData, message, handleChange, handleCoreSkillChange, handleSkillChange, handleSubmit };
};