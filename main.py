from mcp.server.fastmcp import FastMCP

# Create MCP server
mcp = FastMCP("Hello-World-Server")

@mcp.tool()
def say_hello(name: str) -> str:
    """Say hello to someone by name."""
    return f"Hello {name}! 🎉 Welcome to MCP!"

@mcp.tool()
def get_time() -> str:
    """Get current timestamp."""
    from datetime import datetime
    return f"Current time: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}"

@mcp.tool()
def echo(message: str) -> str:
    """Echo back any message."""
    return f"Echo: {message}"

if __name__ == "__main__":
    mcp.run(transport="http")
