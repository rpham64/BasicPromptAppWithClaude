import os

import anthropic
from dotenv import load_dotenv
from fastapi import FastAPI

load_dotenv()
api_key = os.getenv("ANTHROPIC_API_KEY")

if api_key is None:
    raise RuntimeError("ANTHROPIC_API_KEY is not set. Add it to your .env file.")

client = anthropic.Anthropic()
app = FastAPI()

@app.get("/prompt")
async def send_prompt(prompt: str) -> str:
    try:
        response = client.messages.create(
            model="claude-sonnet-5",
            max_tokens=200,
            messages=[
                {
                    "role": "user",
                    "content": prompt
                }
            ],
        )

        for block in response.content:
            if block.type == "text":
                return block.text

        return "Did not find text response"
    except Exception as error:
        return str(error)