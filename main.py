import asyncio
import os
import logging
from mcp.server.fastmcp import FastMCP

logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

mcp = FastMCP("Hello-World-MCP")

@mcp.tool()
def hello(name: str) -> str:
    """Greet a user by name."""
    return f"Hello {name}! 🎉 Cloud Run MCP live!"

@mcp.tool()
def echo(msg: str) -> str:
    """Echo message back."""
    return f"Echo: {msg}"

if __name__ == "__main__":
    port = int(os.environ.get("PORT", 8080))
    logger.info(f"🚀 Starting MCP server on port {port}")
    
    # CLOUD RUN REQUIRED: async + host="0.0.0.0"
    asyncio.run(
        mcp.run_async(
            transport="streamable-http",
            host="0.0.0.0",
            port=port
        )
    )
