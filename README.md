# VITAS VPN

Android VPN app project. Free/open development setup with GitHub Actions APK builds.

## Current status

Version 0.1.0 is the first UI scaffold: country selector and Kill Switch preference preview. **It is not yet a working VPN**: no WireGuard tunnel, server credentials, or VPN backend are included yet. The country names are placeholders, not live server locations.

## Build

Push to `main` or run the workflow manually from **Actions → Build VITAS VPN APK → Run workflow**. Download the `VITAS-VPN-debug` artifact from the completed run.

## Next engineering milestone

Integrate a properly licensed WireGuard tunnel client and connect it to servers that VITAS owns or is explicitly authorized to use. Proton Free servers are not automatically available for third-party apps.
