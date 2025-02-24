import {Agent} from "../api/getCharacterInfo.ts";

export const characterData = (character: Agent | null) => {
    return character ? {
        name: character.name,
        role: character.role,
        elementType: character.elementType,
    } : { name: "UNKNOWN", role: "UNKNOWN", elementType: "UNKNOWN" };
};