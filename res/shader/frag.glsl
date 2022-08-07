#version 460 core

in vec4 color;
in vec2 uv;


out vec4 FragColor;

uniform sampler2D textureAtlas;

void main() {

    FragColor = texture(textureAtlas, uv)  * color;

}
