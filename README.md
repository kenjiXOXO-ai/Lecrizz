# LecRizz real-app development package

This is a PRIVATE development starting point, not a Play Store release.

## Android app
- Native Android application shell.
- First-launch setup guide: enable keyboard → switch to LecRizz → ready.
- Real `InputMethodService` keyboard.
- Rizz and Translate are separate action buttons ABOVE the normal keyboard keys.
- Space remains a normal key.
- No Fizz.

## Developer dashboard
`backend/` contains a Node/Express + Socket.IO development server. The dashboard shows live presence and basic plan metadata. It deliberately does not show passwords or private messages.

## Premium test plans
- Monthly: ₱50/month TEST
- Yearly: ₱499/year TEST
- Lifetime: ₱1,499 TEST
- Founder: permanent

These are test values and are not configured as real Google Play Billing prices yet.

## Next production work
1. Put the approved v0.8 web UI into the Android app's native screens/WebView as needed.
2. Replace demo Rizz/translation logic with the LecRizz backend AI service.
3. Add account authentication and server-side premium entitlements.
4. Connect Android heartbeat/auth to the dashboard backend.
5. Add Google Play Billing.
6. Replace temporary keyboard implementation with the polished full keyboard UI.
7. Add the user's approved app icon asset to Android launcher resources.
8. Test on physical Android devices before any publishing.
