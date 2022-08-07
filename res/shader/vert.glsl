#version 460 core

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 textPos;
layout (location = 2) in vec4 inVColor;

out vec4 color;
out vec2 uv;


void main() {
    gl_Position = vec4(position, 1.0f);
    uv = textPos;

    color = inVColor;

}
